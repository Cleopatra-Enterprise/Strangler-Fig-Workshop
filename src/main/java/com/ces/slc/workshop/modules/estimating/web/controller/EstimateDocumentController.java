package com.ces.slc.workshop.modules.estimating.web.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.web.controller.AbstractDocumentController;
import com.ces.slc.workshop.modules.core.web.dto.DocumentIdentifierDto;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentMapper;
import com.ces.slc.workshop.modules.estimating.application.EstimateDocumentService;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateDocumentDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

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

    @Override
    public EstimateDocumentService getDocumentService() {
        return (EstimateDocumentService) super.getDocumentService();
    }

    @GetMapping("/{id}/knowledgebases")
    public ResponseEntity<Set<DocumentIdentifierDto>> getReferencedKnowledgebases(
            @PathVariable Long id) {
        return ResponseEntitySupport.fromOptionalCollection(
                getDocumentService().getReferencedKnowledgebases(id),
                getDocumentMapper()::toDocumentIdentifierDto
        );
    }
}
