package com.funix.asm01.springboot.repository;

import com.funix.asm01.springboot.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDonationRepository extends JpaRepository<UserDonation,Long> {
    @Modifying
    @Query(value = "delete from user_donation where user_id = :userId", nativeQuery = true)
    public void deleteUserDonation(@Param("userId") long userId);
    @Query(value = "select * from spring_boot_asm01.user_donation where donation_id = :donationId",nativeQuery = true)
    public List<UserDonation> getUserDonations(@Param("donationId") long donationId );

    @Modifying
    @Query(value = "update user_donation set status = :status where id = :userDonationId", nativeQuery = true)
    public void changeStatus(@Param("userDonationId") long userDonationId, @Param("status") long status);

}
