package com.app.commerce.dto.team.request;

import com.app.commerce.dto.common.request.BasePageFilterRequest;
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
public class GetAllTeamRequest extends BasePageFilterRequest<Team> {

    private String query;


    @Override
    public Specification<Team> getSpecification() {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(query)) {
                String queryPattern = "%" + query.trim().toLowerCase() + "%";
                predicates.add(cb.like(cb.lower(root.get(Team.Fields.name)), queryPattern));
                predicates.add(cb.like(cb.lower(root.get(Team.Fields.code)), queryPattern));
            }
            return !predicates.isEmpty()
                    ? cb.or(predicates.toArray(new Predicate[0]))
                    : cb.and();
        };
    }
}
