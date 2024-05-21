package com.ces.slc.workshop.modules.core.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.support.ResponseEntityUtils;

@RestController
@RequestMapping("/breakdown/structure")
public class BreakdownStructureController {

    private final BreakdownStructureService breakdownStructureService;
    private final BreakdownStructureMapper breakdownStructureMapper;

    public BreakdownStructureController(BreakdownStructureService breakdownStructureService,
            BreakdownStructureMapper breakdownStructureMapper) {
        this.breakdownStructureService = breakdownStructureService;
        this.breakdownStructureMapper = breakdownStructureMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreakdownStructureDto> getBreakdownStructure(@PathVariable Long id) {
        return ResponseEntityUtils.fromOptional(
                breakdownStructureService.getBreakdownStructure(id),
                breakdownStructureMapper::toStructureDto
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreakdownStructureDto> updateBreakdownStructure(@PathVariable Long id, BreakdownStructureDto breakdownStructureDto) {
        return ResponseEntityUtils.fromOptional(
                breakdownStructureService.updateBreakdownStructure(id, breakdownStructureDto),
                breakdownStructureMapper::toStructureDto
        );
    }

    @GetMapping("/{id}/root")
    public ResponseEntity<BreakdownKeyDto> getRootBreakdownKey(@PathVariable Long id) {
        return ResponseEntityUtils.fromOptional(
                breakdownStructureService.getRootBreakdownKey(id),
                breakdownStructureMapper::toKeyDto
        );
    }
}
