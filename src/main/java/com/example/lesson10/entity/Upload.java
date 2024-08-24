package com.example.lesson10.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Upload extends Auditable {
    @Column(nullable = false)
    private String generated_name;
    @Column(nullable = false)
    private String original_name;
    @Column(nullable = false)
    private String mine_type;
    private long size;
    @Column(nullable = false)
    private String extension;

    @Builder(builderMethodName = "childBuilder")
    public Upload(String id, LocalDateTime created_at, LocalDateTime updated_at, String generated_name,
                  String original_name, String mine_type, long size, String extension) {
        super(id, created_at, updated_at);
        this.generated_name = generated_name;
        this.original_name = original_name;
        this.mine_type = mine_type;
        this.size = size;
        this.extension = extension;
    }
}
