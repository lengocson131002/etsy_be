package com.app.commerce.dto.profile.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
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

    private Long teamId;

    @Override
    public Specification<GoLoginProfile> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (teamId != null) {
                predicates.add(cb.equal(
                        root.join(GoLoginProfile.Fields.shop)
                            .join(Shop.Fields.team)
                                .get(Team.Fields.id),
                teamId));
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();

                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.join(GoLoginProfile.Fields.shop).get(Shop.Fields.id)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(GoLoginProfile.Fields.shop).get(Shop.Fields.name)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.name)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.goLoginProfileId)), queryPattern));

                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
