package com.ces.slc.workshop.shared.application.document;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ces.slc.workshop.shared.domain.Document;
import com.ces.slc.workshop.shared.domain.DocumentComponent;

@NoRepositoryBean
public interface DocumentComponentRepository<C extends DocumentComponent>
        extends ListCrudRepository<C, Long>, JpaSpecificationExecutor<C> {

    Optional<C> findByDocumentAndId(Document<C> document, Long id);
}
