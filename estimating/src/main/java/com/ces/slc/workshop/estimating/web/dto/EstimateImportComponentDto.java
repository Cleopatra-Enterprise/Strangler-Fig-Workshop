package com.ces.slc.workshop.estimating.web.dto;

import jakarta.validation.constraints.Min;

public record EstimateImportComponentDto(
        @Min(value = 0, message = "Document ID must be greater than 0")
        Long documentId,
        @Min(value = 0, message = "Component ID must be greater than 0")
        Long componentId
) { }
