package com.ces.slc.workshop.estimating.application;

import java.util.Currency;
import java.util.Map;

import com.ces.slc.workshop.estimating.domain.EstimateDocument;

public record EstimateTotalCalculationResult(EstimateDocument document, Map<Currency, Long> totals) {

    public void addTotal(Currency currency, Long total) {
        totals.put(currency, total);
    }
}
