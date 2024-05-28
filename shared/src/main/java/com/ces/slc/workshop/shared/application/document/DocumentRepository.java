package com.ces.slc.workshop.shared.application.document;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ces.slc.workshop.shared.domain.Document;
import com.ces.slc.workshop.shared.domain.DocumentComponent;

@NoRepositoryBean
public interface DocumentRepository<D extends Document<C>, C extends DocumentComponent> extends ListCrudRepository<D, Long> {
}
