package com.funix.asm01.springboot.repository;

import com.funix.asm01.springboot.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Modifying
    @Query(value = "update spring_boot_asm01.donation set status = :status where id = :donationId", nativeQuery = true)
    public void changeDonationStatus(@Param("donationId")long donationId, @Param("status") long status);
}
