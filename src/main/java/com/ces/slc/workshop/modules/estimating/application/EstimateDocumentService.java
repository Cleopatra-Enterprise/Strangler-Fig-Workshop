package com.ces.slc.workshop.modules.estimating.application;

import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    protected EstimateDocument createNewDocument(DocumentDto documentDto) {
        User user = getCurrentUser();
        EstimateDocument estimateDocument = new EstimateDocument(documentDto.name(), user, LocalDateTime.now());
        estimateDocument.setDescription(documentDto.description());
        return estimateDocument;
    }

    @Override
    protected void updateDocument(EstimateDocument existingDocument, DocumentDto documentDto) {
        copyDocumentMetadata(existingDocument, documentDto);
    }
}
