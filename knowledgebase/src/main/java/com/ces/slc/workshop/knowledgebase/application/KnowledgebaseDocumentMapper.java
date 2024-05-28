package com.ces.slc.workshop.knowledgebase.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseComponentDto;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseDocumentDto;
import com.ces.slc.workshop.shared.application.breakdown.BreakdownStructureMapper;
import com.ces.slc.workshop.shared.application.document.DocumentMapper;

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
