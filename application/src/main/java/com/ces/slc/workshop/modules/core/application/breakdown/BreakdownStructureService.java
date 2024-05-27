package com.ces.slc.workshop.modules.core.application.breakdown;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;

@Service
public class BreakdownStructureService {

    private final BreakdownStructureMapper breakdownStructureMapper;
    private final BreakdownStructureRepository breakdownStructureRepository;
    private final BreakdownKeyRepository breakdownKeyRepository;

    public BreakdownStructureService(
            BreakdownStructureMapper breakdownStructureMapper,
            BreakdownStructureRepository breakdownStructureRepository,
            BreakdownKeyRepository breakdownKeyRepository) {
        this.breakdownStructureMapper = breakdownStructureMapper;
        this.breakdownStructureRepository = breakdownStructureRepository;
        this.breakdownKeyRepository = breakdownKeyRepository;
    }

    public Optional<BreakdownStructure> getBreakdownStructure(Long id) {
        return breakdownStructureRepository.findById(id);
    }

    public Optional<BreakdownKey> getBreakdownKey(Long structureId, Long keyId) {
        return breakdownKeyRepository.findByStructureIdAndId(structureId, keyId);
    }

    public Set<BreakdownStructure> getBreakdownStructures(Document<?> document) {
        return document.getBreakdownStructures();
    }

    @Transactional
    public BreakdownStructure createBreakdownStructure(Document<?> document, BreakdownStructureDto breakdownStructureDto) {
        BreakdownStructure breakdownStructure = breakdownStructureMapper.toBreakdownStructure(breakdownStructureDto);
        breakdownStructure.setDocument(document);

        breakdownKeyRepository.save(breakdownStructure.getRootKey());

        BreakdownStructure persistentStructure = breakdownStructureRepository.save(breakdownStructure);
        breakdownStructure.getRootKey().setStructure(persistentStructure);

        return persistentStructure;
    }

    public Optional<BreakdownStructure> updateBreakdownStructure(Long id, BreakdownStructureDto breakdownStructureDto) {
        return breakdownStructureRepository.findById(id)
                .map(breakdownStructure -> {
                    updateBreakdownStructure(breakdownStructure, breakdownStructureDto);
                    return breakdownStructureRepository.save(breakdownStructure);
                });
    }

    private void updateBreakdownStructure(BreakdownStructure breakdownStructure, BreakdownStructureDto breakdownStructureDto) {
        breakdownStructure.setName(breakdownStructureDto.name());
    }

    public Optional<BreakdownKey> getRootBreakdownKey(Long id) {
        return breakdownStructureRepository.findById(id).map(BreakdownStructure::getRootKey);
    }

    public void deleteBreakdownStructure(Long breakdownStructureId) {
        breakdownStructureRepository.deleteById(breakdownStructureId);
    }

    public Optional<Set<DocumentComponent>> getReferencingComponents(Long structureId) {
        return getBreakdownStructure(structureId).map(breakdownKeyRepository::getReferencingComponents);
    }

    public Optional<Set<DocumentComponent>> getReferencingComponents(Long structureId, Long keyId) {
        return getBreakdownKey(structureId, keyId).map(breakdownKeyRepository::getReferencingComponents);
    }

    public Optional<BreakdownKey> createBreakdownKey(Long structureId, Long keyId, BreakdownKeyDto breakdownKeyDto) {
        return getBreakdownStructure(structureId)
                .map(structure -> keyId == null
                        ? structure.getRootKey()
                        : getBreakdownKey(structureId, keyId).orElse(null)
                ).map(parentKey -> {
                    BreakdownKey breakdownKey = new BreakdownKey(breakdownKeyDto.name(), parentKey);
                    updateBreakdownKey(breakdownKey, breakdownKeyDto);
                    parentKey.addChild(breakdownKey);
                    return breakdownKeyRepository.save(breakdownKey);
                });
    }

    public Optional<BreakdownKey> updateBreakdownKey(Long structureId, Long keyId, BreakdownKeyDto breakdownKeyDto) {
        return getBreakdownKey(structureId, keyId)
                .map(breakdownKey -> {
                    updateBreakdownKey(breakdownKey, breakdownKeyDto);
                    return breakdownKeyRepository.save(breakdownKey);
                });
    }

    private void updateBreakdownKey(BreakdownKey breakdownKey, BreakdownKeyDto breakdownKeyDto) {
        breakdownKey.setName(breakdownKeyDto.name());
        breakdownKey.setDescription(breakdownKeyDto.description());
    }

    public void deleteBreakdownKey(Long structureId, Long keyId) {
        getBreakdownKey(structureId, keyId).ifPresent(breakdownKey -> {
            if (breakdownKey.getParent() == null) {
                throw new IllegalArgumentException("Cannot delete root key");
            }
            breakdownKey.getParent().removeChild(breakdownKey);
            breakdownKeyRepository.delete(breakdownKey);
        });
    }
}
