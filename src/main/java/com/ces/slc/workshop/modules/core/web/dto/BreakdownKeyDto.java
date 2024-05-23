package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;
import java.util.Set;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link BreakdownKey}
 */
public record BreakdownKeyDto(
        Long id,
        @NotNull(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,
        @Size(max = 255, message = "Description must be less than 255 characters")
        String description,
        Set<BreakdownKeyIdentifierDto> children
) implements Serializable { }
