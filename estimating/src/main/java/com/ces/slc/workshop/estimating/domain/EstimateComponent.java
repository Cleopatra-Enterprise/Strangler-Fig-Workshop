package com.ces.slc.workshop.estimating.domain;

import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.shared.domain.BreakdownKey;
import com.ces.slc.workshop.shared.domain.DocumentComponent;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class EstimateComponent extends DocumentComponent {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private EstimateComponent parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<EstimateComponent> children = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private KnowledgebaseComponent knowledgebaseComponent;

    private long quantity;

    protected EstimateComponent() {
        // For JPA
    }

    public EstimateComponent(EstimateDocument document) {
        super(document);
        this.parent = null;
    }

    public EstimateComponent(EstimateDocument document, KnowledgebaseComponent knowledgebaseComponent) {
        super(document);
        this.parent = null;
        this.knowledgebaseComponent = knowledgebaseComponent;
        setValuesFromKnowledgebaseComponent(knowledgebaseComponent);
    }

    private void setValuesFromKnowledgebaseComponent(KnowledgebaseComponent knowledgebaseComponent) {
        setDescription(knowledgebaseComponent.getDescription());
        setReferentialId(knowledgebaseComponent.getReferentialId());
        setCost(knowledgebaseComponent.getCost());
        setCurrency(knowledgebaseComponent.getCurrency());
        for(BreakdownKey breakdownKey : knowledgebaseComponent.getBreakdownKeys()) {
            addBreakdownKey(breakdownKey);
        }
    }

    public EstimateComponent getParent() {
        return parent;
    }

    public Set<EstimateComponent> getChildren() {
        return children;
    }

    public void addChild(EstimateComponent child) {
        child.parent = this;
        children.add(child);
    }

    public void removeChild(EstimateComponent child) {
        child.parent = null;
        children.remove(child);
    }

    public KnowledgebaseComponent getKnowledgebaseComponent() {
        return knowledgebaseComponent;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
