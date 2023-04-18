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

    @JsonIgnore
    private String shopId;

    @Override
    public Specification<Order> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Order.Fields.shop).get(Shop.Fields.id), shopId));
            }

            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
