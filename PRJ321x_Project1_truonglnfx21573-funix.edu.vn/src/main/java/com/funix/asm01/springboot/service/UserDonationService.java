package com.funix.asm01.springboot.service;

import com.funix.asm01.springboot.entity.UserDonation;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDonationService {
    public void deleteUserDonation(long userId);

    public List<UserDonation> getUserDonations(long donationId);// get by id

    public UserDonation getUserDonation(long userDonationId);// get by id

    public List<UserDonation> getUserDonations();// get all
    public void saveUserDonation(UserDonation userDonation);

    public void changeStatus(long userDonationId, long status);

}
