package com.app.commerce.dto.shop.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import jakarta.persistence.criteria.*;
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

    private Boolean tracked;

    private Long trackerId;

    private List<Long> teamIds;

    private List<Long> exceptTeamIds;


    @Override
    public Specification<Shop> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(cb.lower(root.get(Shop.Fields.status)), status.trim()));
            }

            if (trackerId != null && tracked != false) {
                predicates.add(cb.equal(root.join(Shop.Fields.trackers).get(User.Fields.id), trackerId));
            }
            if (tracked != null && trackerId != null) {
                if (tracked) {
                } else {
                    Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
                    Root<Shop> subqueryRoot = subquery.from(Shop.class);
                    subquery.select(subqueryRoot.get(Shop.Fields.id));
                    subquery.where(cb.and(
                            cb.equal(subqueryRoot.join(Shop.Fields.trackers).get(User.Fields.id), trackerId),
                            cb.equal(subqueryRoot.get(Shop.Fields.id), root.get(Shop.Fields.id))
                    ));
                    predicates.add(cb.not(cb.exists(subquery)));
                }
            }

            if (teamIds != null) {
                predicates.add(root.join(Shop.Fields.teams).get(Team.Fields.id).in(teamIds));
            }

            if (exceptTeamIds != null) {
                exceptTeamIds.forEach(id -> {
                    Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
                    Root<Shop> subqueryRoot = subquery.from(Shop.class);
                    subquery.select(subqueryRoot.get(Shop.Fields.id));
                    subquery.where(cb.and(
                            cb.equal(subqueryRoot.join(Shop.Fields.teams).get(Team.Fields.id), id),
                            cb.equal(subqueryRoot.get(Shop.Fields.id), root.get(Shop.Fields.id))
                    ));
                    predicates.add(cb.not(cb.exists(subquery)));
                });
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(Shop.Fields.name)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
