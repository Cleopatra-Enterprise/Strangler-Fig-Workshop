package com.ces.slc.workshop.modules.core.application.document;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.MultiValueMap;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;

import jakarta.persistence.criteria.Join;

public abstract class DocumentComponentSpecificationBuilder<C extends DocumentComponent> {

    public static final String BREAKDOWN_KEYS_PARAM = "breakdownKeys";

    public Set<Specification<C>> buildSpecifications(Long documentId, MultiValueMap<String, String> parameters) {
        HashSet<Specification<C>> specifications = parameters.keySet().stream()
                .map(key -> buildSpecification(key, parameters.get(key)))
                .flatMap(Optional::stream)
                .collect(Collectors.toCollection(HashSet::new));

        // Always filter by document, not allowing to query for components from other documents
        specifications.add(isPartOfDocument(documentId));

        return specifications;
    }

    protected Optional<Specification<C>> buildSpecification(String key, List<String> values) {
        if (BREAKDOWN_KEYS_PARAM.equals(key)) {
            return Optional.of(hasBreakdownKeys(values));
        }
        return Optional.empty();
    }

    @NonNull
    protected Specification<C> isPartOfDocument(Long documentId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("document").get("id"), documentId);
    }

    @NonNull
    private Specification<C> hasBreakdownKeys(List<String> values) {
        return (root, query, criteriaBuilder) -> {
            Join<C, BreakdownKey> join = root.join("breakdownKeys");
            return join.get("id").in(values);
        };
    }
}
