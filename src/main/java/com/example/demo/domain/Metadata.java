package com.example.demo.domain;

import com.example.demo.core.model.AbstractTimeGeneratedIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "metadata")
public class Metadata extends AbstractTimeGeneratedIdEntity{

    private String name;
    @Column(name = "description")
    private String desc;
    @Column(name = "time")
    private Timestamp time;
    private String keywords;
    @Column(name = "link_id")
    private int linkId;
    @Column(name = "user_id")
    private int userId;

    @Transient
    private Link link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Metadata() {
        super();
    }
}
