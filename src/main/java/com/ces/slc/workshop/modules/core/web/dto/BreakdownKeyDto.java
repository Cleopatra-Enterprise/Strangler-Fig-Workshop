package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;
import java.util.Set;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;

/**
 * DTO for {@link BreakdownKey}
 */
public record BreakdownKeyDto(
        Long id,
        String name,
        Set<BreakdownKey> children,
        String description
) implements Serializable { }
