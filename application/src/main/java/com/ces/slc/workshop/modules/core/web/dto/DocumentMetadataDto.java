package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ces.slc.workshop.modules.core.domain.Document;

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
