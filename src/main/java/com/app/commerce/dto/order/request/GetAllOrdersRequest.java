package com.app.commerce.dto.order.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllOrdersRequest extends BasePageFilterRequest<Order> {
    private String shopId;
    private String query;
    private String status;
    private List<Long> teamIds;
    private OffsetDateTime from;
    private OffsetDateTime to;
    private String shopStatus;

    @Override
    public Specification<Order> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Order.Fields.shop).get(Shop.Fields.id), shopId));
            }

            if (StringUtils.isNotBlank(status)) {
                predicates.add(cb.equal(root.get(Order.Fields.progressStep), status));
            }

            if (StringUtils.isNotBlank(query)) {
                List<Predicate> queryPredicates = new ArrayList<>();

                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.etsyOrderId)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.orderName)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.orderEmail)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.shippingAddress)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.shippingCustomerName)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Order.Fields.shop).get(Shop.Fields.id)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Order.Fields.shop).get(Shop.Fields.name)), queryPattern));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (teamIds != null) {
                predicates.add(root.join(Order.Fields.shop)
                        .join(Shop.Fields.teams)
                        .get(Team.Fields.id)
                        .in(teamIds));
            }

            if (from != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Order.Fields.orderTime), from));
            }

            if (to != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(Order.Fields.orderTime), to));
            }

            if (StringUtils.isNotBlank(shopStatus)) {
                predicates.add(cb.equal(root.get(Order.Fields.shop).get(Shop.Fields.status), shopStatus));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
