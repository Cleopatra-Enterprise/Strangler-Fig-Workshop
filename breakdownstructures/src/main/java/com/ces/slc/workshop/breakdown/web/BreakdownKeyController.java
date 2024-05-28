package com.ces.slc.workshop.breakdown.web;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureService;
import com.ces.slc.workshop.shared.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.shared.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.support.ResponseEntitySupport;

@RestController
@RequestMapping("/breakdownstructure")
public class BreakdownKeyController {

    private final BreakdownStructureService breakdownStructureService;
    private final BreakdownStructureMapper breakdownStructureMapper;

    public BreakdownKeyController(BreakdownStructureService breakdownStructureService, BreakdownStructureMapper breakdownStructureMapper) {
        this.breakdownStructureService = breakdownStructureService;
        this.breakdownStructureMapper = breakdownStructureMapper;
    }

    @GetMapping("/{structureId}/keys/{keyId}")
    public ResponseEntity<BreakdownKeyDto> getBreakdownKey(
            @PathVariable Long structureId,
            @PathVariable Long keyId) {
        return ResponseEntitySupport.fromOptional(
                breakdownStructureService.getBreakdownKey(structureId, keyId),
                breakdownStructureMapper::toKeyDto
        );
    }

    @GetMapping("/{structureId}/keys/{keyId}/references")
    public ResponseEntity<Set<DocumentComponentIdentifierDto>> getReferencingComponents(
            @PathVariable Long structureId,
            @PathVariable Long keyId) {
        return ResponseEntitySupport.fromOptionalCollection(
                breakdownStructureService.getReferencingComponents(structureId, keyId),
                breakdownStructureMapper::toComponentIdentifierDto
        );
    }

    @PostMapping("/{structureId}/keys/{keyId}/children")
    public ResponseEntity<BreakdownKeyDto> createBreakdownKey(
            @PathVariable Long structureId,
            @PathVariable Long keyId,
            @RequestBody BreakdownKeyDto breakdownKeyDto) {
        return ResponseEntitySupport.fromOptional(
                breakdownStructureService.createBreakdownKey(structureId, keyId, breakdownKeyDto),
                breakdownStructureMapper::toKeyDto
        );
    }

    @PutMapping("/{structureId}/keys/{keyId}")
    public ResponseEntity<BreakdownKeyDto> updateBreakdownKey(
            @PathVariable Long structureId,
            @PathVariable Long keyId,
            @RequestBody BreakdownKeyDto breakdownKeyDto) {
        return ResponseEntitySupport.fromOptional(
                breakdownStructureService.updateBreakdownKey(structureId, keyId, breakdownKeyDto),
                breakdownStructureMapper::toKeyDto
        );
    }

    @DeleteMapping("/{structureId}/keys/{keyId}")
    public void deleteBreakdownKey(
            @PathVariable Long structureId,
            @PathVariable Long keyId) {
        breakdownStructureService.deleteBreakdownKey(structureId, keyId);
    }
}
