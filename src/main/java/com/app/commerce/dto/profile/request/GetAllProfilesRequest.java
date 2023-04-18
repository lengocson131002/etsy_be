package com.app.commerce.dto.profile.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.GoLoginProfile;
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
                query = query.trim().toLowerCase();
                predicates.add(cb.like(cb.lower(root.get(GoLoginProfile.Fields.name)), "%" + query + "%"));
                predicates.add(cb.like(root.join(GoLoginProfile.Fields.shop).get(Shop.Fields.id), "%" + query + "%"));
                predicates.add(cb.like(root.get(GoLoginProfile.Fields.id), "%" + query + "%"));
            }

            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
