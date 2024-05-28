package com.ces.slc.workshop.shared.application.document;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import com.ces.slc.workshop.shared.domain.BreakdownKey;
import com.ces.slc.workshop.shared.domain.DocumentComponent;

import jakarta.persistence.criteria.Join;

public abstract class DocumentComponentSpecificationBuilder<C extends DocumentComponent> extends AbstractSpecificationBuilder<C> {

    public static final String BREAKDOWN_KEYS_PARAM = "breakdownKeys";

    @Override
    protected Optional<Specification<C>> buildDocumentSpecification(Long documentId) {
        // Always filter by document, not allowing to query for components from other documents
        Specification<C> specification = (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("document").get("id"), documentId);
        };
        return Optional.of(specification);
    }

    @Override
    protected Optional<Specification<C>> buildSpecification(String key, List<String> values) {
        if (BREAKDOWN_KEYS_PARAM.equals(key)) {
            return Optional.of(hasBreakdownKeys(values));
        }
        return Optional.empty();
    }

    @NonNull
    private Specification<C> hasBreakdownKeys(List<String> values) {
        return (root, query, criteriaBuilder) -> {
            Join<C, BreakdownKey> join = root.join("breakdownKeys");
            return join.get("id").in(values);
        };
    }
}
