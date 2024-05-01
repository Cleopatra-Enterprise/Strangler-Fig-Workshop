package com.ces.slc.workshop.modules.core.web.dto;

public interface DocumentComponentDto {

    Long id();

    Long documentId();

    String referentialId();

    String description();

    long cost();

    String currency();
}
