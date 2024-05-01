package com.ces.slc.workshop.modules.estimating.web.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;

public record EstimateDocumentDto(
        Long id,
        String author,
        String description,
        String name,
        LocalDateTime creationTimestamp,
        Set<DocumentComponentIdentifierDto> topLevelComponents,
        Set<BreakdownStructureDto> breakdownStructures
) implements DocumentDto {

}
