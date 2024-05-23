package com.ces.slc.workshop.modules.estimating.web.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.web.controller.AbstractDocumentController;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentMapper;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentService;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateDocumentDto;

@RestController
@Validated
@RequestMapping("/estimate")
public class EstimateDocumentController extends AbstractDocumentController<
        EstimateDocument, EstimateComponent,
        EstimateDocumentDto> {

    protected EstimateDocumentController(
            EstimateDocumentService estimateDocumentService,
            EstimateDocumentMapper documentMapper,
            BreakdownStructureMapper breakdownStructureMapper) {
        super(estimateDocumentService,
                documentMapper, breakdownStructureMapper);
    }
}
