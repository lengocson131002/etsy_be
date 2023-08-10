package com.app.commerce.dto.staff.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Role;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStaffRequest extends BasePageFilterRequest<User> {

    private String query;

    private String role;

    private List<Long> teamIds;

    @JsonIgnore
    private List<String> exceptedRoles;

    private List<Long> exceptTeamIds;

    @Override
    public Specification<User> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(role)) {
                predicates.add(cb.equal(root.join(User.Fields.roles).get(Role.Fields.code), role));
            }

            if (exceptedRoles != null) {
                exceptedRoles.forEach(exceptedRole -> {
                    predicates.add(cb.notEqual(root.join(User.Fields.roles).get(Role.Fields.code), exceptedRole));
                });
            }

            if (teamIds != null) {
                predicates.add(root.join(User.Fields.teams).get(Team.Fields.id).in(teamIds));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.staffId)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.username)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.fullName)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.phoneNumber)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.email)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            if (exceptTeamIds != null) {
                exceptTeamIds.forEach(id -> {
                    Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
                    Root<User> subqueryRoot = subquery.from(User.class);
                    subquery.select(subqueryRoot.get(User.Fields.id));
                    subquery.where(cb.and(
                            cb.equal(subqueryRoot.join(User.Fields.teams).get(Team.Fields.id), id),
                            cb.equal(subqueryRoot.get(User.Fields.id), root.get(User.Fields.id))
                    ));
                    predicates.add(cb.not(cb.exists(subquery)));
                });
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
