package com.ces.slc.workshop.modules.knowledgebase.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseLevelDto;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseLevelIdentifierDto;

@Mapper(componentModel = "spring")
public interface KnowledgebaseLevelMapper {

    @Mapping(target = "parentId", source = "parent.id")
    KnowledgebaseLevelIdentifierDto toLevelIdentifierDto(KnowledgebaseLevel level);

    KnowledgebaseLevelDto toLevelDto(KnowledgebaseLevel level);
}
