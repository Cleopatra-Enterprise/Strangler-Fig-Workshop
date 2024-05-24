package com.ces.slc.workshop.modules.knowledgebase.web.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.web.controller.AbstractDocumentController;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseDocumentMapper;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseDocumentService;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseLevelMapper;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseDocumentDto;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseLevelDto;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseLevelIdentifierDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

@RestController
@RequestMapping("/knowledgebase")
public class KnowledgebaseDocumentController extends AbstractDocumentController<KnowledgebaseDocument, KnowledgebaseComponent, KnowledgebaseDocumentDto> {

    private final KnowledgebaseLevelMapper levelMapper;

    protected KnowledgebaseDocumentController(
            KnowledgebaseDocumentService documentService,
            KnowledgebaseDocumentMapper documentMapper,
            BreakdownStructureMapper breakdownStructureMapper, KnowledgebaseLevelMapper levelMapper) {
        super(documentService, documentMapper, breakdownStructureMapper);
        this.levelMapper = levelMapper;
    }

    @Override
    public KnowledgebaseDocumentService getDocumentService() {
        return (KnowledgebaseDocumentService) super.getDocumentService();
    }

    @GetMapping("/{id}/levels")
    public ResponseEntity<Set<KnowledgebaseLevelIdentifierDto>> getLevels(
            @PathVariable Long id) {
        return ResponseEntitySupport.fromOptionalCollection(
                getDocumentService().getParentLevels(id),
                levelMapper::toLevelIdentifierDto
        );
    }

    @GetMapping("/{id}/levels/default")
    public ResponseEntity<KnowledgebaseLevelDto> getDefaultLevel(@PathVariable Long id) {
        return ResponseEntitySupport.fromOptional(
                getDocumentService().getDefaultLevel(id),
                levelMapper::toLevelDto
        );
    }

    @PostMapping("/{id}/levels")
    public ResponseEntity<KnowledgebaseLevelIdentifierDto> createLevel(
            @PathVariable Long id,
            @RequestBody KnowledgebaseLevelDto levelDto) {
        return ResponseEntitySupport.fromOptional(
                getDocumentService().createLevel(id, levelDto),
                levelMapper::toLevelIdentifierDto
        );
    }
}
