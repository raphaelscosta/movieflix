package com.movieflix.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="streaming")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;

    public Streaming(){

    }

    public Streaming(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Streaming streaming = (Streaming) o;
        return Objects.equals(id, streaming.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
