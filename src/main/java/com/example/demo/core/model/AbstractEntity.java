package com.example.demo.core.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.Transient;
import java.util.Objects;

public abstract class AbstractEntity implements Persistable<Integer> {

    @Transient
    private boolean newEntity;

    // CREATE TABLE .. (
    //        `id` BINARY(16) NOT NULL primary key
    //
    // INSERT INTO ... VALUES(
    //      unhex(replace(uuid(), '-', ''))
    //
    // SELECT hex(id), name FROM ...;

    @Override
    public abstract Integer getId();

    public void setNewEntity(boolean newEntity) {
        this.newEntity = newEntity;
    }

    @Override
    @Transient
    public boolean isNew() {
        return newEntity || getId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (isNew()) {
            return false;
        }

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
