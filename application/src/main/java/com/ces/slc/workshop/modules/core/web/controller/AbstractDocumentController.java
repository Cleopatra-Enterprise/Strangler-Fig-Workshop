package com.ces.slc.workshop.modules.core.web.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.document.AbstractDocumentService;
import com.ces.slc.workshop.modules.core.application.document.DocumentMapper;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentMetadataDto;
import com.ces.slc.workshop.security.domain.User;
import com.ces.slc.workshop.support.ResponseEntitySupport;

import jakarta.validation.Valid;

public abstract class AbstractDocumentController<
        D extends Document<C>, C extends DocumentComponent,
        T extends DocumentDto> {

    private final AbstractDocumentService<D, C> documentService;
    private final DocumentMapper<D, C, ?, ?> documentMapper;
    private final BreakdownStructureMapper breakdownStructureMapper;

    protected AbstractDocumentController(
            AbstractDocumentService<D, C> documentService,
            DocumentMapper<D, C, ?, ?> documentMapper,
            BreakdownStructureMapper breakdownStructureMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
        this.breakdownStructureMapper = breakdownStructureMapper;
    }

    public AbstractDocumentService<D, C> getDocumentService() {
        return documentService;
    }

    public DocumentMapper<D, C, ?, ?> getDocumentMapper() {
        return documentMapper;
    }

    public BreakdownStructureMapper getBreakdownStructureMapper() {
        return breakdownStructureMapper;
    }

    @GetMapping
    public List<DocumentMetadataDto> getAllDocuments() {
        return documentService.getAllDocuments().stream()
                .map(documentMapper::toMetadataDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable Long id) {
        return ResponseEntitySupport.fromOptional(
                documentService.getDocumentById(id),
                documentMapper::toDocumentDto
        );
    }

    @PostMapping
    // Note: User is resolved using com.ces.slc.workshop.security.application.UserArgumentResolver
    public DocumentDto createDocument(@RequestBody T documentDto, User author) {
        return documentMapper.toDocumentDto(documentService.createDocument(author, documentDto));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable Long id, @RequestBody T documentDto) {
        return ResponseEntitySupport.fromOptional(
                documentService.updateDocument(id, documentDto),
                documentMapper::toDocumentDto
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }

    @GetMapping("/{id}/breakdownstructures")
    public ResponseEntity<Set<BreakdownStructureIdentifierDto>> getBreakdownStructures(@PathVariable Long id) {
        return ResponseEntitySupport.fromOptionalCollection(
                documentService.getBreakdownStructures(id),
                breakdownStructureMapper::toStructureIdentifierDto
        );
    }

    @PostMapping("/{id}/breakdownstructures")
    public ResponseEntity<BreakdownStructureDto> addBreakdownStructure(
            @PathVariable Long id,
            @Valid @RequestBody BreakdownStructureDto breakdownStructureDto) {
        return ResponseEntitySupport.fromOptional(
                documentService.addBreakdownStructure(id, breakdownStructureDto),
                breakdownStructureMapper::toStructureDto
        );
    }
}
