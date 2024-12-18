package com.entity;

// import com.enums.Role;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private LocalDate birthday;

    // @ElementCollection // => 'Basic' attribute type should not be a container
    @ManyToMany
    private Set<Role> roles;
}
