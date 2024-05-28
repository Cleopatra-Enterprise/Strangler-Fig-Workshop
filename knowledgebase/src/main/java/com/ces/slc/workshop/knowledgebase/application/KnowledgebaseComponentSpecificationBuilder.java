package com.ces.slc.workshop.knowledgebase.application;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.shared.application.document.DocumentComponentSpecificationBuilder;

import jakarta.persistence.criteria.Join;

@Service
public class KnowledgebaseComponentSpecificationBuilder extends DocumentComponentSpecificationBuilder<KnowledgebaseComponent> {

    public static final String LEVELS_PARAM = "levels";

    @Override
    protected Optional<Specification<KnowledgebaseComponent>> buildSpecification(String key, List<String> values) {
        return super.buildSpecification(key, values).or(() -> {
            if (LEVELS_PARAM.equals(key)) {
                return Optional.of(hasLevels(values));
            }
            return Optional.empty();
        });
    }

    private Specification<KnowledgebaseComponent> hasLevels(List<String> values) {
        return (root, query, criteriaBuilder) -> {
            Join<KnowledgebaseComponent, KnowledgebaseLevel> join = root.join("level");
            return join.get("id").in(values);
        };
    }
}
