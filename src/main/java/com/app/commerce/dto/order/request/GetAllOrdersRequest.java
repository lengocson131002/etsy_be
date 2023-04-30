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

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllOrdersRequest extends BasePageFilterRequest<Order> {

    private String shopId;
    private String query;
    private String status;
    private Long teamId;

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

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.etsyOrderId)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.orderName)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.shippingAddress)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.get(Order.Fields.shippingCustomerName)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Order.Fields.shop).get(Shop.Fields.id)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Order.Fields.shop).get(Shop.Fields.name)), queryPattern));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (teamId != null) {
                predicates.add(cb.equal(
                        root.join(Order.Fields.shop)
                                .join(Shop.Fields.team)
                                .get(Team.Fields.id),
                        teamId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
