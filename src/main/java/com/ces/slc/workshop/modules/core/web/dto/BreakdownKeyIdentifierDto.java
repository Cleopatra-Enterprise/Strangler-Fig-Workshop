package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for {@link BreakdownKey}
 */
public record BreakdownKeyIdentifierDto(
        Long id,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long parentId,
        String name,
        Long breakdownStructureId
) implements Serializable { }
