package com.ces.slc.workshop.modules.core.application.breakdown;

import org.mapstruct.Mapper;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownKeyDto;
import com.ces.slc.workshop.modules.core.web.dto.BreakdownStructureDto;

@Mapper
public interface BreakdownStructureMapper {

    BreakdownStructureDto toStructureDto(BreakdownStructure breakdownStructure);

    BreakdownKeyDto toKeyDto(BreakdownKey breakdownKey);

    BreakdownStructure toBreakdownStructure(BreakdownStructureDto breakdownStructureDto);
}
