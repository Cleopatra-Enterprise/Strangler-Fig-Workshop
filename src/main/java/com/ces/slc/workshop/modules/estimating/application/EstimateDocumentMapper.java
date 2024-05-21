package com.ces.slc.workshop.modules.estimating.application;

import org.mapstruct.Mapper;

import com.ces.slc.workshop.modules.core.application.document.DocumentMapper;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateComponentDto;
import com.ces.slc.workshop.modules.estimating.web.dto.EstimateDocumentDto;

@Mapper(componentModel = "spring")
public interface EstimateDocumentMapper extends DocumentMapper<
        EstimateDocument, EstimateComponent,
        EstimateDocumentDto, EstimateComponentDto> {

}
