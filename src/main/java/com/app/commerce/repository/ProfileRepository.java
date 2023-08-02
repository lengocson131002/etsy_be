package com.app.commerce.repository;

import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.repository.projections.ProfileStatusProjection;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<GoLoginProfile, Long>, JpaSpecificationExecutor<GoLoginProfile> {

    @Override
    Page<GoLoginProfile> findAll(Specification<GoLoginProfile> spec, Pageable pageable);

    boolean existsByGoLoginProfileId(String goLoginProfileId);

    @EntityGraph(attributePaths = {"shops"})
    Optional<GoLoginProfile> findByGoLoginProfileId(String goLoginProfileId);

    @EntityGraph(attributePaths = {"shops"})
    Optional<GoLoginProfile> findById(Long Id);

    @Query("select " +
            " (select count(*) from GoLoginProfile where isLogOut = true) as logoutCount," +
            " (select count(*) from GoLoginProfile where isFailedProxy = true) as failedProxyCount," +
            " (select count(*) from GoLoginProfile where isDeleted = true) as deletedCount," +
            " (select count(*) from GoLoginProfile where isTooManyRequest = true) as tooManyRequestCount," +
            " (select count(*) from GoLoginProfile where isEmpty = true) as emptyCount," +
            " (select count(*) from GoLoginProfile where 1=1 and isLogOut = false and isFailedProxy = false and isDeleted = false and isTooManyRequest = false and isEmpty = false) as syncCount")
    ProfileStatusProjection getProfileStatues();

}
