package com.ces.slc.workshop.modules.core.application.document;

import java.util.Currency;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentDto;

public abstract class AbstractDocumentComponentService<D extends Document<C>, C extends DocumentComponent> {

    private final DocumentComponentRepository<C> documentComponentRepository;
    private final BreakdownStructureService breakdownStructureService;

    protected AbstractDocumentComponentService(
            DocumentComponentRepository<C> documentComponentRepository,
            BreakdownStructureService breakdownStructureService
    ) {
        this.documentComponentRepository = documentComponentRepository;
        this.breakdownStructureService = breakdownStructureService;
    }

    public DocumentComponentRepository<C> getDocumentComponentRepository() {
        return documentComponentRepository;
    }

    public C createDocumentComponent(D document, DocumentComponentDto documentComponentDto) {
        C component = createNewComponent(document, documentComponentDto);
        documentComponentRepository.save(component);
        return component;
    }

    protected abstract C createNewComponent(D document, DocumentComponentDto documentComponentDto);

    protected void copyDocumentMetadata(C existingComponent, DocumentComponentDto documentComponentDto) {
        existingComponent.setReferentialId(documentComponentDto.referentialId());
        existingComponent.setDescription(documentComponentDto.description());
        existingComponent.setCost(documentComponentDto.cost());
        existingComponent.setCurrency(Currency.getInstance(documentComponentDto.currency()));

        Set<BreakdownKeyIdentifierDto> breakdownKeys = documentComponentDto.breakdownKeys();
        if (breakdownKeys != null) {
            resetAndCopyBreakdownKeys(existingComponent, breakdownKeys);
        }
    }

    private void resetAndCopyBreakdownKeys(C existingComponent, Set<BreakdownKeyIdentifierDto> breakdownKeys) {
        Set<BreakdownKey> keysToAssign = new HashSet<>();
        for(BreakdownKeyIdentifierDto breakdownKeyIdentifier : breakdownKeys) {
            Optional<BreakdownKey> breakdownKey = breakdownStructureService.getBreakdownKey(
                    breakdownKeyIdentifier.breakdownStructureId(),
                    breakdownKeyIdentifier.id()
            );
            breakdownKey.ifPresent(keysToAssign::add);
        }
        // Removes existing keys, and sets the new ones in a single (batch) operation. This is more efficient than
        // removing keys in bulk and adding new ones one by one (which would result in multiple SQL operations).
        existingComponent.setBreakdownKeys(keysToAssign);
    }

    public Optional<C> getComponent(D document, Long componentId) {
        return documentComponentRepository.findByDocumentAndId(document, componentId);
    }

    public Optional<C> updateComponent(D document, Long componentId, DocumentComponentDto documentComponentDto) {
        return getComponent(document, componentId)
                .map(component -> {
                    copyDocumentMetadata(component, documentComponentDto);
                    documentComponentRepository.save(component);
                    return component;
                });
    }

    public void deleteComponent(D document, Long componentId) {
        getComponent(document, componentId).ifPresent(documentComponentRepository::delete);
    }
}
