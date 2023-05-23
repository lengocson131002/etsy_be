package com.app.commerce.dto.listing.request;

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
public class GetAllListingsRequest extends BasePageFilterRequest<Listing> {

    private String shopId;

    private String query;

    private String status;

    private List<Long> teamIds;

    @Override
    public Specification<Listing> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Listing.Fields.shop).get(Shop.Fields.id), shopId));
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();

                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(Listing.Fields.title)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Listing.Fields.etsyListingId)), queryPattern));

                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(root.get(Listing.Fields.status), status));
            }

            if (teamIds != null) {
                predicates.add(root.join(Listing.Fields.shop)
                                .join(Shop.Fields.teams)
                                .get(Team.Fields.id)
                                .in(teamIds));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
