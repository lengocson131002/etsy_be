package com.app.commerce.dto.profile.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
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

    @Override
    public Specification<GoLoginProfile> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.join(GoLoginProfile.Fields.shop).get(Shop.Fields.id)), queryPattern));
                predicates.add(cb.like(cb.lower(root.join(GoLoginProfile.Fields.shop).get(Shop.Fields.name)), queryPattern));
                predicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.name)), queryPattern));
                predicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.goLoginProfileId)), queryPattern));
            }

            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
