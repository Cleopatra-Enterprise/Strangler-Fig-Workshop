package com.ces.slc.workshop.knowledgebase.web.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseDocumentService;
import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseLevelMapper;
import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseLevelService;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseLevelDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

@RestController
@RequestMapping("/knowledgebase/{documentId}/levels")
public class KnowledgebaseLevelController {

    private final KnowledgebaseLevelService levelService;
    private final KnowledgebaseDocumentService documentService;
    private final KnowledgebaseLevelMapper levelMapper;

    public KnowledgebaseLevelController(
            KnowledgebaseLevelService levelService,
            KnowledgebaseDocumentService documentService,
            KnowledgebaseLevelMapper levelMapper) {
        this.levelService = levelService;
        this.documentService = documentService;
        this.levelMapper = levelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgebaseLevelDto> getLevel(
            @PathVariable Long documentId,
            @PathVariable Long id) {
        Optional<KnowledgebaseLevel> knowledgebaseLevel = documentService.getDocumentById(documentId).flatMap(document -> {
            return levelService.getKnowledgebaseLevel(document, id);
        });
        return ResponseEntitySupport.fromOptional(knowledgebaseLevel, levelMapper::toLevelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgebaseLevelDto> updateLevel(
            @PathVariable Long documentId,
            @PathVariable Long id,
            @RequestBody KnowledgebaseLevelDto levelDto) {
        Optional<KnowledgebaseLevel> knowledgebaseLevel = documentService.getDocumentById(documentId).flatMap(document -> {
            return levelService.updateKnowledgebaseLevel(document, id, levelDto);
        });
        return ResponseEntitySupport.fromOptional(knowledgebaseLevel, levelMapper::toLevelDto);
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(
            @PathVariable Long documentId,
            @PathVariable Long id) {
        documentService.getDocumentById(documentId).flatMap(document -> {
            return levelService.deleteKnowledgebaseLevel(document, id);
        });
    }
}
