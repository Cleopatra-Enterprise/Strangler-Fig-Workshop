package com.ces.slc.workshop.modules.core.application.breakdown;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.domain.BreakdownKey;
import com.ces.slc.workshop.modules.core.domain.BreakdownStructure;
import com.ces.slc.workshop.modules.core.domain.DocumentComponent;

@Repository
public interface BreakdownKeyRepository extends ListCrudRepository<BreakdownKey, Long> {

    @Query("""
    select c from DocumentComponent c
    join c.breakdownKeys bk
    where bk = :breakdownKey
    and type(c) = :componentType
    """)
    <E extends DocumentComponent> E getReferencingComponents(BreakdownKey breakdownKey, Class<E> componentType);

    @Query("""
    select c from DocumentComponent c
    join c.breakdownKeys bk
    join bk.structure bs
    where bs = :breakdownStructure
    and type(c) = :componentType
    """)
    <E extends DocumentComponent> E getReferencingComponents(BreakdownStructure breakdownStructure, Class<E> componentType);
}
