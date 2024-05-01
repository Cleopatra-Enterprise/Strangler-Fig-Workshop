package com.ces.slc.workshop.modules.core.application.document;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentComponentIdentifierDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentDto;
import com.ces.slc.workshop.modules.core.web.dto.DocumentMetadataDto;
import com.ces.slc.workshop.security.domain.User;

public interface DocumentMapper<D extends Document<C>, C extends DocumentComponent, DD extends DocumentDto, DC extends DocumentComponentDto> {

    DocumentMetadataDto toMetadataDto(D document);

    DD toDocumentDto(D document);

    DocumentComponentIdentifierDto toComponentIdentifierDto(C component);

    DC toComponentDto(C component);

    default String map(User user) {
        return user.getUsername();
    }
}
