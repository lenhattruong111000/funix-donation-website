package com.funix.asm01.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "ROLE_UNIQUE_NAME", columnNames = "role_name")
})
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_name")
    private String roleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
