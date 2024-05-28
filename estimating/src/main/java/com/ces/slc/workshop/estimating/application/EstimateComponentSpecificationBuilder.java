package com.ces.slc.workshop.estimating.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ces.slc.workshop.shared.application.document.DocumentComponentSpecificationBuilder;
import com.ces.slc.workshop.estimating.domain.EstimateComponent;

import jakarta.persistence.criteria.Join;

@Service
public class EstimateComponentSpecificationBuilder extends DocumentComponentSpecificationBuilder<EstimateComponent> {

    public static final String TOP_LEVEL_ONLY_PARAM = "topLevelOnly";

    @Override
    protected Optional<Specification<EstimateComponent>> buildSpecification(String key, List<String> values) {
        return super.buildSpecification(key, values).or(() -> {
            if (TOP_LEVEL_ONLY_PARAM.equals(key)) {
                if (values.size() != 1) {
                    throw new IllegalArgumentException("Only one value is allowed for " + TOP_LEVEL_ONLY_PARAM);
                }
                boolean topLevelOnly = Boolean.parseBoolean(values.get(0));
                if (topLevelOnly) {
                    return Optional.of(onlyIfTopLevelComponent());
                }
            }
            return Optional.empty();
        });
    }

    private Specification<EstimateComponent> onlyIfTopLevelComponent() {
        return (root, query, criteriaBuilder) -> {
            Join<EstimateComponent, EstimateComponent> join = root.join("parent");
            return join.isNotNull();
        };
    }
}
