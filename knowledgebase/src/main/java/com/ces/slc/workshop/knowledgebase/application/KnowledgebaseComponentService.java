package com.ces.slc.workshop.knowledgebase.application;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.shared.application.document.AbstractDocumentComponentService;
import com.ces.slc.workshop.shared.web.dto.DocumentComponentDto;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseComponentDto;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseLevelIdentifierDto;

@Service
public class KnowledgebaseComponentService extends AbstractDocumentComponentService<KnowledgebaseDocument, KnowledgebaseComponent> {

    private final KnowledgebaseLevelService knowledgebaseLevelService;

    public KnowledgebaseComponentService(
            KnowledgebaseComponentRepository documentComponentRepository,
            BreakdownStructureService breakdownStructureService,
            KnowledgebaseLevelService knowledgebaseLevelService) {
        super(documentComponentRepository, breakdownStructureService);
        this.knowledgebaseLevelService = knowledgebaseLevelService;
    }

    @Override
    protected KnowledgebaseComponent createNewComponent(KnowledgebaseDocument document, DocumentComponentDto documentComponentDto) {
        KnowledgebaseComponent knowledgebaseComponent = new KnowledgebaseComponent(document);
        copyDocumentMetadata(knowledgebaseComponent, documentComponentDto);
        if (knowledgebaseComponent.getLevel() == null) {
            knowledgebaseComponent.setLevel(document.getDefaultLevel());
        }
        return knowledgebaseComponent;
    }

    @Override
    protected void copyDocumentMetadata(KnowledgebaseComponent existingComponent, DocumentComponentDto documentComponentDto) {
        super.copyDocumentMetadata(existingComponent, documentComponentDto);
        if(documentComponentDto instanceof KnowledgebaseComponentDto knowledgebaseComponentDto) {
            KnowledgebaseLevelIdentifierDto level = knowledgebaseComponentDto.level();
            if (level != null) {
                knowledgebaseLevelService
                        .getKnowledgebaseLevel(existingComponent.getKnowledgebaseDocument(), level.id())
                        .ifPresent(existingComponent::setLevel);
            }
        }
    }
}
