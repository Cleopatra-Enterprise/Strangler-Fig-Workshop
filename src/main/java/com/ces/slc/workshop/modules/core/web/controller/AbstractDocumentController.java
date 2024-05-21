package com.ces.slc.workshop.modules.core.web.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.document.AbstractDocumentService;
import com.ces.slc.workshop.modules.core.application.document.DocumentMapper;
import com.ces.slc.workshop.modules.core.application.document.DocumentComponentSpecificationBuilder;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentMetadataDto;
import com.ces.slc.workshop.support.ResponseEntityUtils;

public abstract class AbstractDocumentController<D extends Document<C>, C extends DocumentComponent, T extends DocumentDto> {

    private final AbstractDocumentService<D, C> documentService;
    private final DocumentMapper<D, C, ?, ?> documentMapper;
    private final BreakdownStructureMapper breakdownStructureMapper;
    private final DocumentComponentSpecificationBuilder<C> specificationBuilder;

    protected AbstractDocumentController(
            AbstractDocumentService<D, C> documentService,
            DocumentMapper<D, C, ?, ?> documentMapper,
            BreakdownStructureMapper breakdownStructureMapper,
            DocumentComponentSpecificationBuilder<C> specificationBuilder) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
        this.breakdownStructureMapper = breakdownStructureMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @GetMapping
    public List<DocumentMetadataDto> getAllDocuments() {
        return documentService.getAllDocuments().stream()
                .map(documentMapper::toMetadataDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable Long id) {
        return ResponseEntityUtils.fromOptional(
                documentService.getDocumentById(id),
                documentMapper::toDocumentDto
        );
    }

    @PostMapping
    public DocumentDto createDocument(@RequestBody T documentDto) {
        return documentMapper.toDocumentDto(documentService.createDocument(documentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable Long id, @RequestBody T documentDto) {
        return ResponseEntityUtils.fromOptional(
                documentService.updateDocument(id, documentDto),
                documentMapper::toDocumentDto
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }

    @GetMapping("/{id}/components")
    public ResponseEntity<Set<DocumentComponentIdentifierDto>> getComponents(
            @PathVariable Long id,
            @RequestParam(required = false) MultiValueMap<String, String> queryParameters) {
        Set<Specification<C>> specifications = specificationBuilder.buildSpecifications(id, queryParameters);
        return ResponseEntityUtils.fromOptionalCollection(
                documentService.getTopLevelComponents(id, specifications),
                documentMapper::toComponentIdentifierDto
        );
    }

    @GetMapping("/{id}/breakdownstructures")
    public ResponseEntity<Set<BreakdownStructureDto>> getBreakdownStructures(@PathVariable Long id) {
        return ResponseEntityUtils.fromOptionalCollection(
                documentService.getBreakdownStructures(id),
                breakdownStructureMapper::toStructureDto
        );
    }

    @PostMapping("/{id}/breakdownstructures")
    public ResponseEntity<BreakdownStructureDto> addBreakdownStructure(@PathVariable Long id, @RequestBody BreakdownStructureDto breakdownStructureDto) {
        return ResponseEntityUtils.fromOptional(
                documentService.addBreakdownStructure(id, breakdownStructureDto),
                breakdownStructureMapper::toStructureDto
        );
    }

    @DeleteMapping("/{id}/breakdownstructures/{breakdownStructureId}")
    public ResponseEntity<Void> deleteBreakdownStructure(@PathVariable Long id, @PathVariable Long breakdownStructureId) {
        boolean deleted = documentService.deleteBreakdownStructure(id, breakdownStructureId);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
