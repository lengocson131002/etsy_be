package com.app.commerce.dto.shop.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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

    private List<Long> teamIds;

    @Override
    public Specification<Shop> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            root.join(Shop.Fields.trackers, JoinType.LEFT).join(User.Fields.trackings, JoinType.LEFT);
            criteriaQuery.distinct(true);

            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(cb.lower(root.get(Shop.Fields.status)), status.trim()));
            }

            if (trackerId != null) {
                predicates.add(cb.equal(root.join(Shop.Fields.trackers).get(User.Fields.id), trackerId));
            }

            if (teamIds != null) {
                predicates.add(root.join(Shop.Fields.teams).get(Team.Fields.id).in(teamIds));
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(Shop.Fields.name)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            criteriaQuery.distinct(true);

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
