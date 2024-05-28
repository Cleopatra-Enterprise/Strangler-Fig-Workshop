package com.ces.slc.workshop.shared.domain;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DocumentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Document<?> document;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<BreakdownKey> breakdownKeys = new HashSet<>();

    private String referentialId;

    private String description;

    private long cost;

    private Currency currency;

    protected DocumentComponent() {
        // For JPA
    }

    public DocumentComponent(Document<?> document) {
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public Document<?> getDocument() {
        return document;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferentialId() {
        return referentialId;
    }

    public void setReferentialId(String referentialId) {
        this.referentialId = referentialId;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<BreakdownKey> getBreakdownKeys() {
        return breakdownKeys;
    }

    public void addBreakdownKey(BreakdownKey breakdownKey) {
        breakdownKeys.add(breakdownKey);
    }

    public void removeBreakdownKey(BreakdownKey breakdownKey) {
        breakdownKeys.remove(breakdownKey);
    }

    public void setBreakdownKeys(Set<BreakdownKey> breakdownKeys) {
        this.breakdownKeys = breakdownKeys;
    }
}
