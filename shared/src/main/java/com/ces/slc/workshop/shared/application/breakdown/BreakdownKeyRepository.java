package com.ces.slc.workshop.shared.application.breakdown;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.shared.domain.BreakdownKey;
import com.ces.slc.workshop.shared.domain.BreakdownStructure;
import com.ces.slc.workshop.shared.domain.DocumentComponent;

@Repository
public interface BreakdownKeyRepository extends ListCrudRepository<BreakdownKey, Long> {

    @Query("""
    select c from DocumentComponent c
    join c.breakdownKeys bk
    where bk = :breakdownKey
    and c.document = bk.structure.document
    """)
    Set<DocumentComponent> getReferencingComponents(BreakdownKey breakdownKey);

    @Query("""
    select c from DocumentComponent c
    join c.breakdownKeys bk
    join bk.structure bs
    where bs = :breakdownStructure
    and c.document = bs.document
    """)
    Set<DocumentComponent> getReferencingComponents(BreakdownStructure breakdownStructure);

    @Query("""
    select bk from BreakdownKey bk
    where bk.structure.id = :structureId
    and bk.id = :keyId
    """)
    Optional<BreakdownKey> findByStructureIdAndId(Long structureId, Long keyId);
}
