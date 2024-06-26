package com.ces.slc.workshop.modules.core.application.document;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.security.domain.User;

import jakarta.transaction.Transactional;

public abstract class AbstractDocumentService<D extends Document<C>, C extends DocumentComponent> {

    private final DocumentRepository<D, C> documentRepository;
    private final DocumentComponentRepository<C> documentComponentRepository;
    private final BreakdownStructureService breakdownStructureService;
    private final AbstractDocumentComponentService<D, C> documentComponentService;

    protected AbstractDocumentService(
            DocumentRepository<D, C> documentRepository,
            DocumentComponentRepository<C> documentComponentRepository,
            BreakdownStructureService breakdownStructureService,
            AbstractDocumentComponentService<D, C> documentComponentService) {
        this.documentRepository = documentRepository;
        this.documentComponentRepository = documentComponentRepository;
        this.breakdownStructureService = breakdownStructureService;
        this.documentComponentService = documentComponentService;
    }

    public BreakdownStructureService getBreakdownStructureService() {
        return breakdownStructureService;
    }

    public AbstractDocumentComponentService<D, C> getDocumentComponentService() {
        return documentComponentService;
    }

    public DocumentRepository<D, C> getDocumentRepository() {
        return documentRepository;
    }

    public DocumentComponentRepository<C> getDocumentComponentRepository() {
        return documentComponentRepository;
    }

    public List<D> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<D> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Optional<Set<C>> getComponents(Long id) {
        return documentRepository.findById(id)
                .map(D::getComponents);
    }

    public Optional<Set<C>> getComponents(Long id, Set<Specification<C>> specifications) {
        if (specifications.isEmpty()) {
            return getComponents(id);
        }
        if (!documentRepository.existsById(id)) {
            return Optional.empty();
        }
        Specification<C> specification = specifications.stream()
                .reduce(Specification::and)
                .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        return Optional.of(Set.copyOf(documentComponentRepository.findAll(specification)));
    }

    public Optional<C> addComponent(Long id, DocumentComponentDto documentComponentDto) {
        return documentRepository.findById(id)
                .map(document -> {
                    C documentComponent = documentComponentService.createDocumentComponent(document, documentComponentDto);
                    document.addComponent(documentComponent);
                    documentRepository.save(document);
                    return documentComponent;
                });
    }

    public D createDocument(User author, DocumentDto documentDto) {
        D document = createNewDocument(author, documentDto);
        return documentRepository.save(document);
    }

    protected abstract D createNewDocument(User author, DocumentDto documentDto);

    @Transactional
    public Optional<D> updateDocument(Long id, DocumentDto documentDto) {
        return documentRepository.findById(id)
                .map(document -> {
                    updateDocument(document, documentDto);
                    return documentRepository.save(document);
                });
    }

    protected abstract void updateDocument(D existingDocument, DocumentDto documentDto);

    protected void copyDocumentMetadata(D existingDocument, DocumentDto documentDto) {
        existingDocument.setDescription(documentDto.description());
        existingDocument.setName(documentDto.name());
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public Optional<Set<BreakdownStructure>> getBreakdownStructures(Long id) {
        return documentRepository.findById(id).map(breakdownStructureService::getBreakdownStructures);
    }

    public Optional<BreakdownStructure> addBreakdownStructure(Long id, BreakdownStructureDto breakdownStructureDto) {
        return documentRepository.findById(id)
                .map(document -> {
                    BreakdownStructure breakdownStructure = breakdownStructureService.createBreakdownStructure(document, breakdownStructureDto);
                    document.addBreakdownStructure(breakdownStructure);
                    documentRepository.save(document);
                    return breakdownStructure;
                });
    }

    public Optional<C> getComponent(Long id, Long componentId) {
        return documentRepository.findById(id)
                .flatMap(document -> documentComponentService.getComponent(document, componentId));
    }

    public <CT extends DocumentComponentDto> Optional<C> updateComponent(Long id, Long componentId, CT documentComponentDto) {
        return documentRepository.findById(id)
                .flatMap(document -> documentComponentService.updateComponent(document, componentId, documentComponentDto));
    }

    public void deleteComponent(Long id, Long componentId) {
        documentRepository.findById(id)
                .ifPresent(document -> documentComponentService.deleteComponent(document, componentId));
    }
}
