package com.funix.asm01.springboot.service.implement;

import com.funix.asm01.springboot.entity.Donation;
import com.funix.asm01.springboot.repository.DonationRepository;
import com.funix.asm01.springboot.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;
    @Override
    public List<Donation> getDonations() {
        return this.donationRepository.findAll();
    }

    @Override
    public void saveDonation(Donation donation) {
        this.donationRepository.save(donation);
    }

    @Override
    public Donation getDonation(long donationId) {
        Optional<Donation> optional = this.donationRepository.findById(donationId);
        Donation donation = null;
        if (optional.isPresent()){
            donation= optional.get();
        }else {
            throw new RuntimeException("Cannot found donation by "+donationId);
        }
        return donation;
    }

    @Override
    public void changeDonationStatus(long donationId, long status) {
        this.donationRepository.changeDonationStatus(donationId,status);
    }

    @Override
    public void deleteDonation(long donationId) {
        this.donationRepository.deleteById(donationId);
    }

}
