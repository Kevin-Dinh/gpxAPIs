package com.example.demo.domain;

import sun.plugin.javascript.navig.Link;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class Metadata {
    private static final long serialVersionUID = 2L;

    private String name;
    private String description;
    private String author;
    private List<Link> links;
    private ZonedDateTime time;
    private String keywords;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(name, metadata.name) &&
                Objects.equals(description, metadata.description) &&
                Objects.equals(author, metadata.author) &&
                Objects.equals(links, metadata.links) &&
                Objects.equals(time, metadata.time) &&
                Objects.equals(keywords, metadata.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, author, links, time, keywords);
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", links=" + links +
                ", time=" + time +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
