package com.app.commerce.dto.listing.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Order;
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

    private String shopId;

    private String query;

    private String status;

    @Override
    public Specification<Listing> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Listing.Fields.shop).get(Shop.Fields.id), shopId));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(Listing.Fields.title)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Listing.Fields.shop).get(Shop.Fields.id)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Listing.Fields.shop).get(Shop.Fields.name)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Listing.Fields.etsyListingId)), queryPattern));

                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(root.get(Listing.Fields.status), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
