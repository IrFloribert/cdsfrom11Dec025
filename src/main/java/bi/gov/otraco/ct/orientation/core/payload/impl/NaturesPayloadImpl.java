package bi.gov.otraco.ct.orientation.core.payload.impl;

import bi.gov.otraco.ct.orientation.cmd.api.command.NatureCreatedCommand;
import bi.gov.otraco.ct.orientation.core.common.NAtureCode;
import bi.gov.otraco.ct.orientation.core.dto.FieldsValidatorResponse;
import bi.gov.otraco.ct.orientation.core.dto.MessageResponse;
import bi.gov.otraco.ct.orientation.core.exception.RemoteApiException;
import bi.gov.otraco.ct.orientation.core.feign.validator.command.ValidationCommand;
import bi.gov.otraco.ct.orientation.core.payload.NaturesPayload;
import bi.gov.otraco.ct.orientation.query.api.repository.NatureRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Service
@RequiredArgsConstructor
public class NaturesPayloadImpl implements NaturesPayload {
    private final NatureRepository repository;

    @Override
    public Mono<Void> createException(NatureCreatedCommand cmd) {
        return validateChassisNumber(cmd.chassisNo()).then(validatePlateNumber(cmd.plateNo())).then(validateReceiptNumber(cmd.receiptNo())).then();
    }

    private Mono<Void> validateChassisNumber(String chassisNo) {
        return repository.existsByChassisNo(chassisNo).flatMap(exists -> exists ? Mono.error(new IllegalArgumentException("Un véhicule avec le numéro de châssis " + chassisNo + " existe déjà")) : Mono.empty());
    }


    private Mono<Void> validatePlateNumber(String plateNo) {
        return repository.existsByPlateNo(plateNo).flatMap(exists -> exists ? Mono.error(new IllegalArgumentException("Un véhicule avec le numéro d'immatriculation " + plateNo + " existe déjà")) : Mono.empty());
    }

    private Mono<Void> validateReceiptNumber(String receiptNo) {
        return repository.existsByReceiptNo(receiptNo).flatMap(exists -> exists ? Mono.error(new IllegalArgumentException("Un reçu avec le numéro " + receiptNo + " existe déjà")) : Mono.empty());
    }

    @Override
    public Mono<String> getNatureCode() {
        return repository.count().flatMap(count -> {
            if (count == 0) return Mono.just("NT30000000001");
            return repository.findByLogCreatedDesc().take(1).single().map(last -> NAtureCode.generate(last.getCode()));
        });
    }



    WebClient webClientValidNature = WebClient.builder().baseUrl("http://192.168.80.22:8025").build();
    @Override
    public Mono<MessageResponse> validNature(ValidationCommand command) {
        return webClientValidNature.put()
                .uri(
                        "/api/v1/otraco/certificate/operation/validator/validate-nature"
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(MessageResponse.class);
    }


    WebClient webClientValidOrientation = WebClient.builder().baseUrl("http://192.168.80.22:8025").build();
    @Override
    public Mono<?> validOrientation(ValidationCommand command) {
        return webClientValidOrientation.put()
                .uri(
                        "/api/v1/otraco/certificate/operation/validator/validate-orientation"
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(MessageResponse.class);
    }


    private final ObjectMapper objectMapper;

    private Mono<? extends Throwable> handleError(ClientResponse response) {
        HttpStatus status = (HttpStatus) response.statusCode();

        return response.bodyToMono(JsonNode.class)
                .flatMap(root -> {

                    if (root.has("fieldsValidator")) {
                        FieldsValidatorResponse validation =
                                objectMapper.convertValue(root, FieldsValidatorResponse.class);
                        return Mono.error(new RemoteApiException(status, validation));
                    }

                    if (root.has("message")) {
                        return Mono.error(new RemoteApiException(status, root.get("message").asText()));
                    }

                    return Mono.error(new RemoteApiException(status, "Remote service error"));
                });
    }
}


