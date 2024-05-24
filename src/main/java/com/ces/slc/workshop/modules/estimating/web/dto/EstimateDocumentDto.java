package com.ces.slc.workshop.modules.estimating.web.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstimateDocumentDto(
        Long id,
        String author,
        LocalDateTime creationTimestamp,

        @Size(max = 255, message = "Description must be less than 255 characters")
        String description,

        @NotNull(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        Set<DocumentComponentIdentifierDto> components,
        Set<BreakdownStructureDto> breakdownStructures
) implements DocumentDto {

}
