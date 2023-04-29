package com.app.commerce.dto.shop.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllShopRequest extends BasePageFilterRequest<Shop> {
    private String query;

    private String status;

    private Long trackerId;

    private Long teamId;

    @Override
    public Specification<Shop> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(cb.lower(root.get(Shop.Fields.status)), status.trim()));
            }

            if (trackerId != null) {
                predicates.add(cb.equal(root.join(Shop.Fields.trackers).get(User.Fields.id), trackerId));
            }

            if (teamId != null) {
                predicates.add(cb.equal(root.join(Shop.Fields.team).get(Team.Fields.id), teamId));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(Shop.Fields.name)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
