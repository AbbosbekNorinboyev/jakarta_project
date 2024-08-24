package com.example.lesson10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
//    @Builder.Default
    private String role = "User";
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(String id, LocalDateTime created_at, LocalDateTime updated_at,
                    String email, String password, String role, Status status) {
        super(id, created_at, updated_at);
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public enum Status {
        IN_ACTIVE,
        ACTIVE,
        BLOCKED
    }
}



