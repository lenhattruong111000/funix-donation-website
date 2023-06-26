package com.funix.asm01.springboot.repository;

import com.funix.asm01.springboot.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //get user by userId
    @Query(value = "select * from spring_boot_asm01.user e where e.user_name= :username", nativeQuery = true)
    public AppUser findUserAccount(@Param("username") String username);

    // lock account : status = 0
    @Modifying
    @Query(value = "update spring_boot_asm01.user set status = 0 where id= :id", nativeQuery = true)
    public void lockAccount(@Param("id") long id);

    // unlock account : status = 1
    @Modifying
    @Query(value = "update spring_boot_asm01.user set status = 1 where id= :id", nativeQuery = true)
    public void unlockAccount(@Param("id") long id);

    //delete account
    @Modifying
    @Query(value = "delete from spring_boot_asm01.user where id = :id", nativeQuery = true)
    public void deleteAccount(@Param("id") long id);

    //update account
    @Modifying
    @Query(value = "update spring_boot_asm01.user set " +
            "full_name = :fullName, " +
            "address = :address, " +
            "phone_number= :phoneNumber, role_id = :roleId " +
            "where id= :userId",nativeQuery = true)
    public void updateAccount(@Param("userId") long userId, @Param("fullName") String fullName,
                              @Param("address") String address, @Param("phoneNumber")String phoneNumber, @Param("roleId") long roleId);

}
