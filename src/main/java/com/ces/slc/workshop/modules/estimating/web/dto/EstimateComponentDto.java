package com.ces.slc.workshop.modules.estimating.web.dto;

import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentDto;

public record EstimateComponentDto(
        Long id,
        Long documentId,
        String referentialId,
        String description,
        long cost,
        String currency,
        long quantity
) implements DocumentComponentDto { }
