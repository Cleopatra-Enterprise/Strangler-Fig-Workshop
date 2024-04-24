package com.ces.slc.workshop.modules.core.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Optional<BreakdownStructureDto> getBreakdownStructure(Long id) {
        return breakdownStructureRepository.findById(id).map(breakdownStructureMapper::toDto);
    }
}
