package com.ces.slc.workshop.modules.core.application.breakdown;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.Document;
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

    public Set<BreakdownStructure> getBreakdownStructures(Document<?> document) {
        return document.getBreakdownStructures();
    }

    public BreakdownStructure createBreakdownStructure(BreakdownStructureDto breakdownStructureDto) {
        BreakdownStructure breakdownStructure = breakdownStructureMapper.toBreakdownStructure(breakdownStructureDto);
        return breakdownStructureRepository.save(breakdownStructure);
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
}
