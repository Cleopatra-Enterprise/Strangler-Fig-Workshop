package com.ces.slc.workshop.knowledgebase.web.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

public record KnowledgebaseLevelDto(
        Long id,
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        KnowledgebaseLevelIdentifierDto parent,
        Set<KnowledgebaseLevelIdentifierDto> children
) { }
