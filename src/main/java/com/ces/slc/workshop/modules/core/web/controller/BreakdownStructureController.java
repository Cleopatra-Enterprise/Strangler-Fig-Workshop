package com.ces.slc.workshop.modules.core.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ces.slc.workshop.modules.core.application.BreakdownStructureService;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;

@Controller
@RequestMapping("/breakdown-structure")
public class BreakdownStructureController {

    private final BreakdownStructureService breakdownStructureService;

    public BreakdownStructureController(BreakdownStructureService breakdownStructureService) {
        this.breakdownStructureService = breakdownStructureService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreakdownStructureDto> getBreakdownStructure(@PathVariable Long id) {
        return breakdownStructureService.getBreakdownStructure(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
