package com.ces.slc.workshop.modules.estimating.application;

import java.util.Currency;
import java.util.Map;

import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;

public record EstimateTotalCalculationResult(EstimateDocument document, Map<Currency, Long> totals) {

    public void addTotal(Currency currency, Long total) {
        totals.put(currency, total);
    }
}
