package com.ces.slc.workshop.knowledgebase.application;

import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.shared.application.document.DocumentRepository;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;

@Repository
public interface KnowledgebaseDocumentRepository extends DocumentRepository<KnowledgebaseDocument, KnowledgebaseComponent> {

}
