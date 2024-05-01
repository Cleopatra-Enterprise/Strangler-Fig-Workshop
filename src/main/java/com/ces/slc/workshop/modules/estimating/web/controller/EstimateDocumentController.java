package com.ces.slc.workshop.modules.estimating.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.document.DocumentComponentSpecificationBuilder;
import com.ces.slc.workshop.modules.core.web.controller.AbstractDocumentController;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentMapper;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentService;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateDocumentDto;

@Controller
@RequestMapping("/estimate")
public class EstimateDocumentController extends AbstractDocumentController<EstimateDocument, EstimateComponent, EstimateDocumentDto> {

    protected EstimateDocumentController(
            EstimateDocumentService estimateDocumentService,
            EstimateDocumentMapper documentMapper,
            BreakdownStructureMapper breakdownStructureMapper,
            DocumentComponentSpecificationBuilder<EstimateComponent> specificationBuilder) {
        super(estimateDocumentService, documentMapper, breakdownStructureMapper, specificationBuilder);
    }
}
