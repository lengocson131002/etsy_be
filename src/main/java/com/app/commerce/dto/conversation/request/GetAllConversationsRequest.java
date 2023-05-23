package com.app.commerce.dto.conversation.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.*;
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
public class GetAllConversationsRequest extends BasePageFilterRequest<Conversation> {

    private String shopId;

    private String query;

    private List<Long> teamIds;

    private OffsetDateTime from;

    private OffsetDateTime to;

    @Override
    public Specification<Conversation> getSpecification() {
        return (root, criteriaQuery, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(shopId)) {
                predicates.add(cb.equal(root.join(Listing.Fields.shop).get(Shop.Fields.id), shopId));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                queryPredicates.add(cb.like(cb.lower(root.get(Conversation.Fields.customerName)), queryPattern));
                queryPredicates.add(cb.like(cb.lower(root.join(Conversation.Fields.shop).get(Shop.Fields.name)), queryPattern));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (teamIds != null) {
                predicates.add(root.join(Conversation.Fields.shop)
                                .join(Shop.Fields.teams)
                                .get(Team.Fields.id).in(teamIds));
            }

            if (from != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(Conversation.Fields.lastMessageTime), from));
            }

            if (to != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(Conversation.Fields.lastMessageTime), to));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
