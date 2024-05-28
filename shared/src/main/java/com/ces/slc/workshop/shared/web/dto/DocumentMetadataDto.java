package com.ces.slc.workshop.shared.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ces.slc.workshop.shared.domain.Document;

/**
 * DTO for {@link Document}
 */
public record DocumentMetadataDto(
        Long id,
        String author,
        String description,
        String name,
        LocalDateTime creationTimestamp
) implements Serializable { }
