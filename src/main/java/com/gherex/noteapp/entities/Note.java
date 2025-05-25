package com.gherex.noteapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    private String title;

    @Setter
    @NotNull
    @Lob
    private String content;

    @Setter
    private Boolean archived;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Note() {
    }

    public Note(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Boolean isArchived() {
        return this.archived;
    }
}
