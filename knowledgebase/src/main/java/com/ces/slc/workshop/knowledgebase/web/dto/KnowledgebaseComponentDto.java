package com.ces.slc.workshop.knowledgebase.web.dto;

import java.util.Set;

import com.ces.slc.workshop.shared.web.dto.BreakdownKeyIdentifierDto;
import com.ces.slc.workshop.shared.web.dto.DocumentComponentDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record KnowledgebaseComponentDto(
        Long id,
        Long documentId,

        @NotNull(message = "Referential ID is required")
        String referentialId,

        @Size(max = 255, message = "Description must be less than 255 characters")
        String description,

        @Min(value = 0, message = "Cost must be greater than or equal to 0")
        long cost,

        @NotNull(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be exactly 3 characters")
        String currency,

        KnowledgebaseLevelIdentifierDto level,
        Set<BreakdownKeyIdentifierDto> breakdownKeys
) implements DocumentComponentDto { }
