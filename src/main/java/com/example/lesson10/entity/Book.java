package com.example.lesson10.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book extends Auditable {
    private String title;
    private String description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Upload file;

    @Builder(builderMethodName = "childBuilder")
    public Book(String id, LocalDateTime created_at, LocalDateTime updated_at, String title,
                String description, Upload file) {
        super(id, created_at, updated_at);
        this.title = title;
        this.description = description;
        this.file = file;
    }
}
