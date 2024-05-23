package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link BreakdownStructure}
 */
public record BreakdownStructureDto(
        Long id,
        @NotNull(message = "Root key is required")
        BreakdownKeyIdentifierDto rootKey,
        Long documentId,
        @NotNull(message = "Name is required")
        String name
) implements Serializable { }
