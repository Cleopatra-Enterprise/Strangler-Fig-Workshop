package com.ces.slc.workshop.shared.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.shared.domain.DocumentComponent;

/**
 * DTO for {@link DocumentComponent}
 */
public record DocumentComponentIdentifierDto(Long id, String referentialId) implements Serializable {

}
