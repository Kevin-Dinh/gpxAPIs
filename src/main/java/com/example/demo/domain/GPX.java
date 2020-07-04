package com.example.demo.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gpx")
public class GPX {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "link")
    private String link;
    @Column(name = "time")
    private Timestamp time;

    @Transient
    private ArrayList<Track> tracks = new ArrayList<>();

    @Column(name = "way_point_id")
    private Integer wayPointId;
    @Column(name = "track_id")
    private Integer trackId;
    @Column(name = "last_modified_at")
    private Timestamp lastModifiedAt;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public Integer getWayPointId() {
        return wayPointId;
    }

    public void setWayPointId(Integer wayPointId) {
        this.wayPointId = wayPointId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Timestamp getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Timestamp lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public GPX() {
    }
    /**
     * Adds a track to the collection.
     *
     * <p>The track is added to the end of the collection. If it already
     * existed in the collection before, it is added again.</p>
     *
     * @param track the track to be added
     */
    public void addTrack(Track track) {
        tracks.add(track);
    }

    /**
     * Returns the list of track that make up the collection.
     *
     * @return a list of tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }
}
