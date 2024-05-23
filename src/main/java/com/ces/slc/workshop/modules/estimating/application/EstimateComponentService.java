package com.ces.slc.workshop.modules.estimating.application;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.application.document.AbstractDocumentComponentService;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentDto;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateComponentDto;

@Service
public class EstimateComponentService extends AbstractDocumentComponentService<EstimateDocument, EstimateComponent> {

    protected EstimateComponentService(
            EstimateComponentRepository documentComponentRepository,
            BreakdownStructureService breakdownStructureService
    ) {
        super(documentComponentRepository, breakdownStructureService);
    }

    @Override
    protected EstimateComponent createNewComponent(EstimateDocument document, DocumentComponentDto documentComponentDto) {
        EstimateComponent estimateComponent = new EstimateComponent(document);
        copyDocumentMetadata(estimateComponent, documentComponentDto);
        return estimateComponent;
    }

    @Override
    protected void copyDocumentMetadata(EstimateComponent existingComponent, DocumentComponentDto documentComponentDto) {
        super.copyDocumentMetadata(existingComponent, documentComponentDto);
        if (documentComponentDto instanceof EstimateComponentDto estimateComponentDto) {
            existingComponent.setQuantity(estimateComponentDto.quantity());
        }
    }

    public Optional<Set<EstimateComponent>> getComponentChildren(EstimateDocument document, Long componentId) {
        return getComponent(document, componentId).map(EstimateComponent::getChildren);
    }

    @Transactional
    public Optional<EstimateComponent> createComponentChild(EstimateDocument document, Long componentId,
            EstimateComponentDto componentDto) {
        return getComponent(document, componentId).map(component -> {
            EstimateComponent child = createDocumentComponent(document, componentDto);
            component.addChild(child);
            getDocumentComponentRepository().saveAll(Set.of(child, component));
            return child;
        });
    }
}
