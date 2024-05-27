package com.ces.slc.workshop.modules.knowledgebase.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record KnowledgebaseLevelIdentifierDto(
        Long id,
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long parentId
) {

}
