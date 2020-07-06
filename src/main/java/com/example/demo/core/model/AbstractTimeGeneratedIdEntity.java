package com.example.demo.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.Instant;

public abstract class AbstractTimeGeneratedIdEntity extends AbstractGeneratedIdEntity{
    @CreatedDate
    @JsonIgnore
    @Column(name = "created_at", nullable = false, insertable = true, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @JsonIgnore
    @Column(name = "last_modified_at", nullable = false, insertable = true, updatable = true)
    private Instant lastModifiedAt;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
