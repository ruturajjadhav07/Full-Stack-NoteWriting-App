package com.relation.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notes")
public class Notes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "notes_id")
    private int id;

    @Column(name = "note_title", nullable = false)
    private String title;

    @Column(name = "note_content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date date;

    @Column(name = "note_completed", nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
