package com.ces.slc.workshop.modules.core.application.breakdown;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentIdentifierDto;

@Mapper(componentModel = "spring")
public interface BreakdownStructureMapper {

    @Mapping(target = "documentId", source = "document.id")
    BreakdownStructureDto toStructureDto(BreakdownStructure breakdownStructure);

    @Mapping(target = "documentId", source = "document.id")
    BreakdownStructureIdentifierDto toStructureIdentifierDto(BreakdownStructure breakdownStructure);

    BreakdownKeyDto toKeyDto(BreakdownKey breakdownKey);

    BreakdownStructure toBreakdownStructure(BreakdownStructureDto breakdownStructureDto);

    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "breakdownStructureId", source = "structure.id")
    BreakdownKeyIdentifierDto toKeyIdentifierDto(BreakdownKey breakdownKey);

    DocumentComponentIdentifierDto toComponentIdentifierDto(DocumentComponent component);
}
