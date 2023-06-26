package com.funix.asm01.springboot.service.implement;

import com.funix.asm01.springboot.entity.UserDonation;
import com.funix.asm01.springboot.repository.UserDonationRepository;
import com.funix.asm01.springboot.service.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService {
    @Autowired
    private UserDonationRepository userDonationRepository;
    @Override
    public void deleteUserDonation(long userId) {
        this.userDonationRepository.deleteUserDonation(userId);
    }

    @Override
    public List<UserDonation> getUserDonations(long donationId) {
        return this.userDonationRepository.getUserDonations(donationId);
    }

    @Override
    public UserDonation getUserDonation(long userDonationId) {
        Optional<UserDonation> optional = this.userDonationRepository.findById(userDonationId);
        UserDonation userDonation = null;
        if (optional.isPresent()){
            userDonation =optional.get();
        }else {
            throw new RuntimeException("can not found userDonation by id:"+userDonationId);
        }
        return userDonation;
    }

    @Override
    public List<UserDonation> getUserDonations() {
        return this.userDonationRepository.findAll();
    }

    @Override
    public void saveUserDonation(UserDonation userDonation){
        this.userDonationRepository.save(userDonation);
    }

    @Override
    public void changeStatus(long userDonationId, long status) {
        this.userDonationRepository.changeStatus(userDonationId, status);
    }
}
