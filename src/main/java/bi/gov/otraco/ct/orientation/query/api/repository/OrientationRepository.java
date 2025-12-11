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
    @Query(value = "{ 'log_created': { $regex: '^?0' } }", sort = "{'log_created':-1}")
    Flux<Orientation> findAllToday(@Param("0") String datePattern);
    Flux<Orientation>  findAllBybranchCode(@Param("agence") String agence);
    @Query(value = "{ $and: [ { 'log_created': { $regex: '^?0' } }, { 'branch_code': ?1 } ] }", sort = "{'log_created':-1}")
    Flux<Orientation> findAllTodayBybranchCode(@Param("date") String date, @Param("agency") String agency);


}