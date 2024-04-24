package com.ces.slc.workshop.modules.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BreakdownStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BreakdownKey rootKey;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Document<?> document;

    private String name;

    protected BreakdownStructure() {
        // For JPA
    }

    public BreakdownStructure(Document<?> document, BreakdownKey rootKey) {
        this.document = document;
        this.rootKey = rootKey;
    }

    public Long getId() {
        return id;
    }

    public BreakdownKey getRootKey() {
        return rootKey;
    }

    public Document<?> getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
