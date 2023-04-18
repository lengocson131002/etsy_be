package com.app.commerce.dto.listing.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllListingsRequest extends BasePageFilterRequest<Listing> {

    @JsonIgnore
    private String shopId;

    @Override
    public Specification<Listing> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Listing.Fields.shop).get(Shop.Fields.id), shopId));
            }

            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
