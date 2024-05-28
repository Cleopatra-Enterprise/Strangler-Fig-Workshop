package com.ces.slc.workshop.estimating.web.controller;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.estimating.application.EstimateComponentSpecificationBuilder;
import com.ces.slc.workshop.estimating.application.EstimateDocumentMapper;
import com.ces.slc.workshop.estimating.application.EstimateDocumentService;
import com.ces.slc.workshop.estimating.web.dto.EstimateDocumentDto;
import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.shared.web.controller.AbstractDocumentController;
import com.ces.slc.workshop.shared.web.dto.DocumentIdentifierDto;
import com.ces.slc.workshop.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.estimating.web.dto.EstimateTotalCalculationResultDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

@RestController
@Validated
@RequestMapping("/estimate")
public class EstimateDocumentController extends AbstractDocumentController<
        EstimateDocument, EstimateComponent,
        EstimateDocumentDto> {

    private final EstimateComponentSpecificationBuilder specificationBuilder;

    public EstimateDocumentController(
            EstimateDocumentService estimateDocumentService,
            EstimateDocumentMapper documentMapper,
            BreakdownStructureMapper breakdownStructureMapper,
            EstimateComponentSpecificationBuilder specificationBuilder) {
        super(estimateDocumentService,
                documentMapper, breakdownStructureMapper);
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public EstimateDocumentService getDocumentService() {
        return (EstimateDocumentService) super.getDocumentService();
    }

    @Override
    public EstimateDocumentMapper getDocumentMapper() {
        return (EstimateDocumentMapper) super.getDocumentMapper();
    }

    @GetMapping("/{id}/knowledgebases")
    public ResponseEntity<Set<DocumentIdentifierDto>> getReferencedKnowledgebases(
            @PathVariable Long id) {
        return ResponseEntitySupport.fromOptionalCollection(
                getDocumentService().getReferencedKnowledgebases(id),
                getDocumentMapper()::toDocumentIdentifierDto
        );
    }

    @PostMapping("/{id}/total")
    public ResponseEntity<EstimateTotalCalculationResultDto> calculateTotals(
            @PathVariable Long id,
            @RequestParam(required = false) MultiValueMap<String, String> queryParameters) {
        Set<Specification<EstimateComponent>> specifications = specificationBuilder.buildSpecifications(id, queryParameters);
        return ResponseEntitySupport.fromOptional(
                getDocumentService().calculateTotals(id, specifications),
                getDocumentMapper()::toCalculationResultDto
        );
    }

    @GetMapping("/references/{knowledgebaseId}")
    public ResponseEntity<Set<DocumentIdentifierDto>> getReferencingDocuments(@PathVariable Long knowledgebaseId) {
        return ResponseEntitySupport.fromOptionalCollection(
                getDocumentService().getReferencingDocuments(knowledgebaseId),
                getDocumentMapper()::toDocumentIdentifierDto
        );
    }
}
