package com.app.commerce.dto.conversation.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllConversationsRequest extends BasePageFilterRequest<Conversation> {

    private String shopId;

    private String query;


    @Override
    public Specification<Conversation> getSpecification() {
        return (root, criteriaQuery, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Listing.Fields.shop).get(Shop.Fields.id), shopId));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(Conversation.Fields.customerName)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
