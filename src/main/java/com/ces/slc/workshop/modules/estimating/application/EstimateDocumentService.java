package com.ces.slc.workshop.modules.estimating.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.application.document.AbstractDocumentService;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateComponentDto;
import com.ces.slc.workshop.security.domain.User;

@Service
public class EstimateDocumentService extends AbstractDocumentService<EstimateDocument, EstimateComponent> {

    protected EstimateDocumentService(
            EstimateDocumentMapper documentMapper,
            EstimateDocumentRepository documentRepository,
            EstimateComponentRepository documentComponentRepository,
            BreakdownStructureService breakdownStructureService,
            EstimateComponentService estimateComponentService) {
        super(documentMapper, documentRepository, documentComponentRepository, breakdownStructureService, estimateComponentService);
    }

    @Override
    protected EstimateDocument createNewDocument(User author, DocumentDto documentDto) {
        EstimateDocument estimateDocument = new EstimateDocument(documentDto.name(), author, LocalDateTime.now());
        copyDocumentMetadata(estimateDocument, documentDto);
        return estimateDocument;
    }

    @Override
    protected void updateDocument(EstimateDocument existingDocument, DocumentDto documentDto) {
        copyDocumentMetadata(existingDocument, documentDto);
    }

    public Optional<Set<EstimateComponent>> getComponentChildren(Long id, Long componentId) {
        return getDocumentById(id)
                .flatMap(document -> getDocumentComponentService().getComponentChildren(document, componentId));
    }

    @Override
    public EstimateComponentService getDocumentComponentService() {
        return (EstimateComponentService) super.getDocumentComponentService();
    }

    public Optional<EstimateComponent> createComponentChild(Long id, Long componentId, EstimateComponentDto componentDto) {
        return getDocumentById(id)
                .flatMap(document -> getDocumentComponentService().createComponentChild(document, componentId, componentDto));
    }
}
