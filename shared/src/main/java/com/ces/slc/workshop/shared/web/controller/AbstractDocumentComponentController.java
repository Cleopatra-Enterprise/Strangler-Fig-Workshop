package com.ces.slc.workshop.shared.web.controller;

import java.util.Set;

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

import com.ces.slc.workshop.shared.application.document.AbstractDocumentService;
import com.ces.slc.workshop.shared.application.document.DocumentComponentSpecificationBuilder;
import com.ces.slc.workshop.shared.application.document.DocumentMapper;
import com.ces.slc.workshop.shared.domain.Document;
import com.ces.slc.workshop.shared.domain.DocumentComponent;
import com.ces.slc.workshop.shared.web.dto.DocumentComponentDto;
import com.ces.slc.workshop.shared.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

import jakarta.validation.Valid;

public abstract class AbstractDocumentComponentController<
        D extends Document<C>, C extends DocumentComponent,
        CT extends DocumentComponentDto> {

    private final DocumentComponentSpecificationBuilder<C> specificationBuilder;
    private final AbstractDocumentService<D, C> documentService;
    private final DocumentMapper<D, C, ?, ?> documentMapper;

    protected AbstractDocumentComponentController(DocumentComponentSpecificationBuilder<C> specificationBuilder,
            AbstractDocumentService<D, C> documentService, DocumentMapper<D, C, ?, ?> documentMapper) {
        this.specificationBuilder = specificationBuilder;
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    public DocumentMapper<D, C, ?, ?> getDocumentMapper() {
        return documentMapper;
    }

    public AbstractDocumentService<D, C> getDocumentService() {
        return documentService;
    }

    @GetMapping("/{id}/components")
    public ResponseEntity<Set<DocumentComponentIdentifierDto>> getComponents(
            @PathVariable Long id,
            @RequestParam(required = false) MultiValueMap<String, String> queryParameters) {
        Set<Specification<C>> specifications = specificationBuilder.buildSpecifications(id, queryParameters);
        return ResponseEntitySupport.fromOptionalCollection(
                documentService.getComponents(id, specifications),
                documentMapper::toComponentIdentifierDto
        );
    }

    @GetMapping("/{id}/components/{componentId}")
    public ResponseEntity<DocumentComponentDto> getComponent(
            @PathVariable Long id,
            @PathVariable Long componentId) {
        return ResponseEntitySupport.fromOptional(
                documentService.getComponent(id, componentId),
                documentMapper::toComponentDto
        );
    }

    @PutMapping("/{id}/components/{componentId}")
    public ResponseEntity<DocumentComponentDto> updateComponent(
            @PathVariable Long id,
            @PathVariable Long componentId,
            @Valid @RequestBody CT documentComponentDto) {
        return ResponseEntitySupport.fromOptional(
                documentService.updateComponent(id, componentId, documentComponentDto),
                documentMapper::toComponentDto
        );
    }

    @PostMapping("/{id}/components")
    public ResponseEntity<DocumentComponentDto> addComponent(
            @PathVariable Long id,
            @Valid @RequestBody CT documentComponentDto) {
        return ResponseEntitySupport.fromOptional(
                documentService.addComponent(id, documentComponentDto),
                documentMapper::toComponentDto
        );
    }

    @DeleteMapping("/{id}/components/{componentId}")
    public void deleteComponent(
            @PathVariable Long id,
            @PathVariable Long componentId) {
        documentService.deleteComponent(id, componentId);
    }
}
