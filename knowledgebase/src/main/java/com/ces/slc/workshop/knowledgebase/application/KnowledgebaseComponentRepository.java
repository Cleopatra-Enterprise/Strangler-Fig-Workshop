package com.ces.slc.workshop.knowledgebase.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.shared.application.document.DocumentComponentRepository;

@Repository
public interface KnowledgebaseComponentRepository extends DocumentComponentRepository<KnowledgebaseComponent> {

}
