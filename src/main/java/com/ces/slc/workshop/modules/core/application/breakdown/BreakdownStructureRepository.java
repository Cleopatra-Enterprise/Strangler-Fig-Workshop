package com.ces.slc.workshop.modules.core.application.breakdown;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;

@Repository
public interface BreakdownStructureRepository extends ListCrudRepository<BreakdownStructure, Long> {

}
