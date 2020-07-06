package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "link")
public class Link extends AbstractTimeGeneratedIdEntity {

    private String href;
    private String text;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Link() {
        super();
    }
}
