package com.ces.slc.workshop.modules.knowledgebase.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.modules.core.web.controller.AbstractDocumentComponentController;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseComponentSpecificationBuilder;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseDocumentMapper;
import com.ces.slc.workshop.modules.knowledgebase.application.KnowledgebaseDocumentService;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.modules.knowledgebase.web.dto.KnowledgebaseComponentDto;

@RestController
@RequestMapping("/knowledgebase")
public class KnowledgebaseDocumentComponentController extends AbstractDocumentComponentController<KnowledgebaseDocument, KnowledgebaseComponent, KnowledgebaseComponentDto> {

    protected KnowledgebaseDocumentComponentController(
            KnowledgebaseComponentSpecificationBuilder specificationBuilder,
            KnowledgebaseDocumentService documentService,
            KnowledgebaseDocumentMapper documentMapper) {
        super(specificationBuilder, documentService, documentMapper);
    }
}
