package com.ces.slc.workshop.modules.estimating.application;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.application.document.AbstractDocumentService;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.security.domain.User;

@Service
public class EstimateDocumentService extends AbstractDocumentService<EstimateDocument, EstimateComponent> {

    protected EstimateDocumentService(
            EstimateDocumentMapper documentMapper,
            EstimateDocumentRepository documentRepository,
            BreakdownStructureService breakdownStructureService) {
        super(documentMapper, documentRepository, breakdownStructureService);
    }

    @Override
    protected EstimateDocument createNewDocument(DocumentDto documentDto) {
        String author = documentDto.author();
        // TODO: Resolve user from author, or from current security context
        User user = null;
        EstimateDocument estimateDocument = new EstimateDocument(documentDto.description(), user, LocalDateTime.now());
        estimateDocument.setName(documentDto.name());
        return estimateDocument;
    }

    @Override
    protected void updateDocument(EstimateDocument existingDocument, DocumentDto documentDto) {
        copyDocumentMetadata(existingDocument, documentDto);
    }
}
