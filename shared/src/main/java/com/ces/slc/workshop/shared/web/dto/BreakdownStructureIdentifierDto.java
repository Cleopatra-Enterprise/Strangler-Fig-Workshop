package com.ces.slc.workshop.shared.web.dto;

public record BreakdownStructureIdentifierDto(
        Long id,
        BreakdownKeyIdentifierDto rootKey,
        Long documentId,
        String name
) { }
