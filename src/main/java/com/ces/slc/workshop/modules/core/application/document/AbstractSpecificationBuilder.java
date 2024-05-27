package com.ces.slc.workshop.modules.core.application.document;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

public abstract class AbstractSpecificationBuilder<T> {

    public Set<Specification<T>> buildSpecifications(Long documentId, MultiValueMap<String, String> parameters) {
        Set<Specification<T>> specifications = parameters.keySet().stream()
                .map(key -> buildSpecification(key, parameters.get(key)))
                .flatMap(Optional::stream)
                .collect(Collectors.toCollection(HashSet::new));

        Optional<Specification<T>> documentSpecification = buildDocumentSpecification(documentId);
        documentSpecification.ifPresent(specifications::add);
        return specifications;
    }

    protected abstract Optional<Specification<T>> buildDocumentSpecification(Long documentId);

    protected abstract Optional<Specification<T>> buildSpecification(String key, List<String> values);
}
