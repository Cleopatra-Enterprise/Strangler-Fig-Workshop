package com.ces.slc.workshop.modules.knowledgebase.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ces.slc.workshop.modules.core.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.modules.core.application.document.DocumentMapper;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseComponentDto;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseDocumentDto;

@Mapper(componentModel = "spring", uses = {
        BreakdownStructureMapper.class,
        KnowledgebaseLevelMapper.class
})
public interface KnowledgebaseDocumentMapper extends DocumentMapper<
        KnowledgebaseDocument, KnowledgebaseComponent,
        KnowledgebaseDocumentDto, KnowledgebaseComponentDto> {

    @Override
    @Mapping(target = "documentId", source = "document")
    KnowledgebaseComponentDto toComponentDto(KnowledgebaseComponent component);
}
