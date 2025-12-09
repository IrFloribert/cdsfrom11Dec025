package bi.gov.otraco.ct.orientation.query.api.repository;

import bi.gov.otraco.ct.orientation.core.model.Orientation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrientationRepository extends ReactiveMongoRepository<Orientation, String> {
    Mono<Boolean> existsByOrientationCode(@Param("orientationCode") String orientationCode);
    Mono<Orientation> findByOrientationCode(@Param("orientationCode") String orientationCode);
    @Query(value = "{}", sort = "{'orientationCode':-1}")
    Flux<Orientation> findByOrientationCodeDesc();
}