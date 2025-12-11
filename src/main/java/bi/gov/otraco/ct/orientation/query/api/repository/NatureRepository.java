package bi.gov.otraco.ct.orientation.query.api.repository;

import bi.gov.otraco.ct.orientation.core.model.Nature;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface NatureRepository extends ReactiveMongoRepository<Nature, String> {
    Mono<Boolean> existsByChassisNo(@Param("chassisNo") String chassisNo);
    Mono<Nature> findByChassisNo(@Param("chassisNo") String chassisNo);
    Mono<Nature> findByPlateNo(@Param("plateNo") String plateNo);
    Mono<Boolean> existsByPlateNo(@Param("plateNo") String plateNo);
    @Query(value = "{}", sort = "{'logCreated':-1}")
    Flux<Nature> findByLogCreatedDesc();
    Mono<Nature> findByCode(@Param("code") String code);
    Mono<Boolean> existsByReceiptNo(@Param("receiptNo") String receiptNo);
    Mono<Nature> findByReceiptNo(@Param("receiptNo") String receiptNo);
}
