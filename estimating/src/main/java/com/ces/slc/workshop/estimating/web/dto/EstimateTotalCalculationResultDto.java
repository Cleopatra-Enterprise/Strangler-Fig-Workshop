package com.ces.slc.workshop.estimating.web.dto;

import java.util.Map;

public record EstimateTotalCalculationResultDto(long documentId, Map<String, Long> total) {

}
