package com.ces.slc.workshop.support;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    public static <T> ResponseEntity<T> fromOptional(Optional<T> optional) {
        return optional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public static <T, D> ResponseEntity<D> fromOptional(Optional<T> optional, Function<T, D> dtoMapper) {
        return optional
                .map(dtoMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public static <T, D> ResponseEntity<Set<D>> fromOptionalCollection(Optional<Set<T>> optionalCollection, Function<T, D> dtoMapper) {
        return optionalCollection
                .map(collection -> collection.stream()
                        .map(dtoMapper)
                        .collect(Collectors.toSet()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
