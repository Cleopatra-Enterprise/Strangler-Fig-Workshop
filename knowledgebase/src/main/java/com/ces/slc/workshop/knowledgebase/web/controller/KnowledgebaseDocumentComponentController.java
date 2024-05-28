package com.ces.slc.workshop.knowledgebase.web.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseDocumentMapper;
import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseDocumentService;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseComponentDto;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseComponentReferenceDto;
import com.ces.slc.workshop.shared.web.controller.AbstractDocumentComponentController;
import com.ces.slc.workshop.knowledgebase.application.KnowledgebaseComponentSpecificationBuilder;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.support.ResponseEntitySupport;

@RestController
@RequestMapping("/knowledgebase")
public class KnowledgebaseDocumentComponentController extends AbstractDocumentComponentController<KnowledgebaseDocument, KnowledgebaseComponent, KnowledgebaseComponentDto> {

    public KnowledgebaseDocumentComponentController(
            KnowledgebaseComponentSpecificationBuilder specificationBuilder,
            KnowledgebaseDocumentService documentService,
            KnowledgebaseDocumentMapper documentMapper) {
        super(specificationBuilder, documentService, documentMapper);
    }

    @Override
    public KnowledgebaseDocumentMapper getDocumentMapper() {
        return (KnowledgebaseDocumentMapper) super.getDocumentMapper();
    }

    @Override
    public KnowledgebaseDocumentService getDocumentService() {
        return (KnowledgebaseDocumentService) super.getDocumentService();
    }
}
