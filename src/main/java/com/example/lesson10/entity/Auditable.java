package com.example.lesson10.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
