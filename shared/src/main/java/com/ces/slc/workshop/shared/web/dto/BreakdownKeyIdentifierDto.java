package com.ces.slc.workshop.shared.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.shared.domain.BreakdownKey;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link BreakdownKey}
 */
public record BreakdownKeyIdentifierDto(
        @NotNull(message = "ID is required")
        @Min(value = 0, message = "ID must be greater than or equal to 0")
        Long id,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long parentId,
        String name,
        @NotNull(message = "Breakdown Structure ID is required")
        @Min(value = 0, message = "Breakdown Structure ID must be greater than or equal to 0")
        Long breakdownStructureId
) implements Serializable { }
