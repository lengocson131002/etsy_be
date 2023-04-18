package com.app.commerce.dto.shop.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Shop;
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

    @Override
    public Specification<Shop> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(this.query)) {
                this.query = this.query.trim().toLowerCase();
                predicates.add(cb.like(cb.lower(root.get(Shop.Fields.name)), "%" + this.query + "%"));
            }
            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
