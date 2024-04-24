package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;

/**
 * DTO for {@link BreakdownStructure}
 */
public record BreakdownStructureDto(
        Long id,
        BreakdownKeyDto rootKey,
        Long documentId,
        String name
) implements Serializable { }
