package com.ces.slc.workshop.shared.web.dto;

import java.time.LocalDateTime;
import java.util.Set;

public interface DocumentDto {

    Long id();

    String author();

    String description();

    String name();

    LocalDateTime creationTimestamp();

    Set<DocumentComponentIdentifierDto> components();

    Set<BreakdownStructureDto> breakdownStructures();
}
