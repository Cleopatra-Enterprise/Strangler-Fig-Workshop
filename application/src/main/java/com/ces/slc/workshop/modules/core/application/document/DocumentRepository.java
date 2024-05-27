package com.ces.slc.workshop.modules.core.application.document;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;

@NoRepositoryBean
public interface DocumentRepository<D extends Document<C>, C extends DocumentComponent> extends ListCrudRepository<D, Long> {
}
