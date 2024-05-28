package com.ces.slc.workshop.shared.web.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface DocumentComponentDto {

    Long id();

    Long documentId();

    String referentialId();

    String description();

    long cost();

    String currency();

    @JsonInclude(Include.NON_ABSENT)
    Set<BreakdownKeyIdentifierDto> breakdownKeys();
}
