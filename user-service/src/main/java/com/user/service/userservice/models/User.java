package com.user.service.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private String userId;
    private String name;
    private String email;
    private String about;

    @Transient
    private List<Rating> ratings;
}
