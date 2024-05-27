package com.ces.slc.workshop.modules.estimating.application;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.document.DocumentMapper;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateComponentDto;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateDocumentDto;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateTotalCalculationResultDto;

@Mapper(componentModel = "spring", uses = {
        BreakdownStructureMapper.class
})
public interface EstimateDocumentMapper extends DocumentMapper<
        EstimateDocument, EstimateComponent,
        EstimateDocumentDto, EstimateComponentDto> {

    @Override
    @Mapping(target = "documentId", source = "document")
    EstimateComponentDto toComponentDto(EstimateComponent component);

    default EstimateTotalCalculationResultDto toCalculationResultDto(EstimateTotalCalculationResult result) {
        return new EstimateTotalCalculationResultDto(result.document().getId(), map(result.totals()));
    }

    default Map<String, Long> map(Map<Currency, Long> currency) {
        Map<String, Long> map = new HashMap<>();
        currency.forEach((key, value) -> map.put(key.getCurrencyCode(), value));
        return map;
    }
}
