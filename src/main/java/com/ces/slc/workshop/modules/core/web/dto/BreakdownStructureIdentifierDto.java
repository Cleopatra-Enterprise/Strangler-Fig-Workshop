package com.ces.slc.workshop.modules.core.web.dto;

public record BreakdownStructureIdentifierDto(
        Long id,
        BreakdownKeyIdentifierDto rootKey,
        Long documentId,
        String name
) { }
