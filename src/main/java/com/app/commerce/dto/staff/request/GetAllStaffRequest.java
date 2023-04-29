package com.app.commerce.dto.staff.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
import com.app.commerce.entity.Role;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class GetAllStaffRequest extends BasePageFilterRequest<User> {

    private String query;

    private String role;

    private Long teamId;

    @JsonIgnore
    private List<String> exceptedRoles;

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

            if (teamId != null) {
                predicates.add(cb.equal(root.join(User.Fields.team).get(Team.Fields.id), teamId));
            }

            List<Predicate> queryPredicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                query = query.trim().toLowerCase();
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.username)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.fullName)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.phoneNumber)), "%" + query + "%"));
                queryPredicates.add(cb.like(cb.lower(root.get(User.Fields.email)), "%" + query + "%"));
                predicates.add(cb.or(queryPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
