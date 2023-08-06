package com.app.commerce.dto.profile.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.enums.ProfileStatus;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllProfilesRequest extends BasePageFilterRequest<GoLoginProfile> {

    private String query;

    private List<Long> teamIds;

    private ProfileStatus status;

    @Override
    public Specification<GoLoginProfile> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (teamIds != null) {
                predicates.add(root.join(GoLoginProfile.Fields.shops)
                        .join(Shop.Fields.teams)
                        .get(Team.Fields.id)
                        .in(teamIds));
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();

                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.name)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.goLoginProfileId)), queryPattern));

                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            // status filter
            if (status != null) {
                switch (status) {
                    case LOGOUT -> predicates.add(cb.and(cb.isTrue(root.get(GoLoginProfile.Fields.isLogOut))));
                    case DELETED -> predicates.add(cb.and(cb.isTrue(root.get(GoLoginProfile.Fields.isDeleted))));
                    case TOO_MANY_REQUEST -> predicates.add(cb.and(cb.isTrue(root.get(GoLoginProfile.Fields.isTooManyRequest))));
                    case EMPTY -> predicates.add(cb.and(cb.isTrue(root.get(GoLoginProfile.Fields.isEmpty))));
                    case FAILED_PROXY -> predicates.add(cb.and(cb.isTrue(root.get(GoLoginProfile.Fields.isFailedProxy))));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
