package com.ces.slc.workshop.modules.estimating.domain;

import java.time.LocalDateTime;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;

@Entity
public class EstimateDocument extends Document<EstimateComponent> {

    protected EstimateDocument() {
        // For JPA
    }

    public EstimateDocument(String description, User author, LocalDateTime creationTimestamp) {
        super(description, author, creationTimestamp);
    }

    @Override
    protected void initialize() {
        // TODO: Implement this method
    }
}
