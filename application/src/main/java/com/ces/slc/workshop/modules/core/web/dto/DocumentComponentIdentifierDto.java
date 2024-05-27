package com.ces.slc.workshop.modules.core.web.dto;

import java.io.Serializable;

import com.ces.slc.workshop.modules.core.domain.DocumentComponent;

/**
 * DTO for {@link DocumentComponent}
 */
public record DocumentComponentIdentifierDto(Long id, String referentialId) implements Serializable {

}
