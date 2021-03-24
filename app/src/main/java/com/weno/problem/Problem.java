package com.weno.problem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    public Problem() {
        this.id = id;
    }

    public Problem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
