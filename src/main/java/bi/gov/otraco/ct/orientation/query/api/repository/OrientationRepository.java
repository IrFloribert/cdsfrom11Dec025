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
    Mono<Boolean> existsByOrientationLineCode(@Param("lineCode") String lineCode);
    Mono<Orientation> findByOrientationCode(@Param("orientationCode") String orientationCode);
    @Query(value = "{}", sort = "{'orientationCode':-1}")
    Flux<Orientation> findByOrientationCodeDesc();
    Flux<Orientation>  findAllBybranchCode(@Param("agence") String agence);
//    Flux<Orientation> findByBranchCode(@Param("date") String date, @Param("branchCode") String branchCode);
    Flux<Orientation> findByLogCreatedAt(@Param("date") String date);


    @Query("{ 'log_created_at' : ?0, 'branch_code' : ?1 }")
    Flux<Orientation> findByBranchCode(String date, String branchCode);

    Mono<Orientation> findByPlateNoAndTinNoAndChassisNo(@Param("plateNo") String plateNo, @Param("tin") String tin, @Param("chassisNo") String chassisNo);
}
