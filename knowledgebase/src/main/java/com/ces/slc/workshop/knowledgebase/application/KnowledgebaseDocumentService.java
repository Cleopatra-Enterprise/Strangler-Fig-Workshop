package com.ces.slc.workshop.knowledgebase.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseLevelDto;
import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.shared.application.document.AbstractDocumentService;
import com.ces.slc.workshop.shared.web.dto.DocumentDto;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.security.domain.User;

@Service
public class KnowledgebaseDocumentService extends AbstractDocumentService<KnowledgebaseDocument, KnowledgebaseComponent> {

    private final KnowledgebaseLevelService knowledgebaseLevelService;

    public KnowledgebaseDocumentService(
            KnowledgebaseDocumentRepository documentRepository,
            KnowledgebaseComponentRepository documentComponentRepository,
            BreakdownStructureService breakdownStructureService,
            KnowledgebaseComponentService documentComponentService,
            KnowledgebaseLevelService knowledgebaseLevelService) {
        super(documentRepository, documentComponentRepository, breakdownStructureService, documentComponentService);
        this.knowledgebaseLevelService = knowledgebaseLevelService;
    }

    @Override
    public KnowledgebaseDocumentRepository getDocumentRepository() {
        return (KnowledgebaseDocumentRepository) super.getDocumentRepository();
    }

    @Override
    public KnowledgebaseComponentRepository getDocumentComponentRepository() {
        return (KnowledgebaseComponentRepository) super.getDocumentComponentRepository();
    }

    @Override
    protected KnowledgebaseDocument createNewDocument(User author, DocumentDto documentDto) {
        KnowledgebaseDocument knowledgebase = new KnowledgebaseDocument(documentDto.name(), author, LocalDateTime.now());
        copyDocumentMetadata(knowledgebase, documentDto);
        knowledgebaseLevelService.createUnassignedKnowledgebaseLevel(knowledgebase);
        return knowledgebase;
    }

    @Override
    protected void updateDocument(KnowledgebaseDocument existingDocument, DocumentDto documentDto) {
        copyDocumentMetadata(existingDocument, documentDto);
    }

    public Optional<Set<KnowledgebaseLevel>> getParentLevels(Long id) {
        return getDocumentById(id).map(KnowledgebaseDocument::getLevels);
    }

    public Optional<KnowledgebaseLevel> getDefaultLevel(Long id) {
        return getDocumentById(id).map(KnowledgebaseDocument::getDefaultLevel);
    }

    public Optional<KnowledgebaseLevel> createLevel(Long id, KnowledgebaseLevelDto levelDto) {
        return getDocumentById(id).map(document -> {
            KnowledgebaseLevel knowledgebaseLevel = knowledgebaseLevelService.createKnowledgebaseLevel(document, levelDto);
            document.addLevel(knowledgebaseLevel);
            getDocumentRepository().save(document);
            return knowledgebaseLevel;
        });
    }
}
