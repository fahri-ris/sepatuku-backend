package com.sepatuku.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;
}
