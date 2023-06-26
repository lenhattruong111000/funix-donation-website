package com.funix.asm01.springboot.repository;

import com.funix.asm01.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select role.name from spring_boot_asm01.role, spring_boot_asm01.user " +
            "where user.role_id = role.id and role.id = :roleId ", nativeQuery = true)
    public String getRoleName(@Param("roleId") long roleId);

}
