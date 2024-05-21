package com.ces.slc.workshop.modules.core.application.document;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.security.domain.CustomUserDetails;
import com.ces.slc.workshop.security.domain.User;

import jakarta.transaction.Transactional;

public abstract class AbstractDocumentService<D extends Document<C>, C extends DocumentComponent> {

    private final DocumentMapper<D, C, ?, ?> documentMapper;
    private final DocumentRepository<D, C> documentRepository;
    private final BreakdownStructureService breakdownStructureService;

    protected AbstractDocumentService(
            DocumentMapper<D, C, ?, ?> documentMapper,
            DocumentRepository<D, C> documentRepository,
            BreakdownStructureService breakdownStructureService) {
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
        this.breakdownStructureService = breakdownStructureService;
    }

    public List<D> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<D> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Optional<Set<C>> getTopLevelComponents(Long id) {
        return documentRepository.findById(id)
                .map(D::getTopLevelComponents);
    }

    public Optional<Set<C>> getTopLevelComponents(Long id, Set<Specification<C>> specifications) {
        if (specifications.isEmpty()) {
            return getTopLevelComponents(id);
        }
        if (!documentRepository.existsById(id)) {
            return Optional.empty();
        }
        Specification<C> specification = specifications.stream()
                .reduce(Specification::and)
                .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        return Optional.of(documentRepository.findAll(specification));
    }

    public D createDocument(DocumentDto documentDto) {
        D document = createNewDocument(documentDto);
        return documentRepository.save(document);
    }

    protected abstract D createNewDocument(DocumentDto documentDto);

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
                    BreakdownStructure breakdownStructure = breakdownStructureService.createBreakdownStructure(breakdownStructureDto);
                    document.addBreakdownStructure(breakdownStructure);
                    documentRepository.save(document);
                    return breakdownStructure;
                });
    }

    public boolean deleteBreakdownStructure(Long id, Long breakdownStructureId) {
        return documentRepository.findById(id)
                .map(document -> {
                    Optional<BreakdownStructure> breakdownStructure = breakdownStructureService.getBreakdownStructure(breakdownStructureId);
                    if (breakdownStructure.isPresent()) {
                        document.removeBreakdownStructure(breakdownStructure.get());
                        documentRepository.save(document);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    protected User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.user();
        }
        else {
            throw new IllegalStateException("Could not resolve user from security context");
        }
    }
}
