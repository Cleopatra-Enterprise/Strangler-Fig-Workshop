package com.ces.slc.workshop.modules.estimating.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.application.document.DocumentRepository;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;

@Repository
public interface EstimateDocumentRepository extends DocumentRepository<EstimateDocument, EstimateComponent> {

    @Query("""
    select distinct component.knowledgebaseComponent
    from EstimateDocument document
    join EstimateComponent component on component.document = document
    where document = :estimateDocument
    """)
    Set<KnowledgebaseComponent> getKnowledgebaseReferences(EstimateDocument estimateDocument);
}
