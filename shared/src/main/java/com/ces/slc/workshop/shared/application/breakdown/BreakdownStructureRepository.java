package com.ces.slc.workshop.shared.application.breakdown;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.shared.domain.BreakdownStructure;

@Repository
public interface BreakdownStructureRepository extends ListCrudRepository<BreakdownStructure, Long> {

}
