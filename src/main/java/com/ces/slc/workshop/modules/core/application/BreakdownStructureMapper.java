package com.ces.slc.workshop.modules.core.application;

import org.mapstruct.Mapper;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;

@Mapper
public interface BreakdownStructureMapper {

    BreakdownStructureDto toDto(BreakdownStructure breakdownStructure);

    BreakdownKeyDto toDto(BreakdownKey breakdownKey);
}
