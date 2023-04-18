package com.app.commerce.dto.order.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
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
public class GetAllOrdersRequest extends BasePageFilterRequest<Order> {

    private String shopId;
    private String query;
    private String status;

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
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(root.get(Order.Fields.orderName), "%" + query + "%"));
                queryPredicates.add(cb.like(root.get(Order.Fields.shippingAddress), "%" + query + "%"));
                queryPredicates.add(cb.like(root.get(Order.Fields.shippingCustomerName), "%" + query + "%"));

                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
