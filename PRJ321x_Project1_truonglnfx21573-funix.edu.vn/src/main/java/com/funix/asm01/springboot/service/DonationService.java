package com.funix.asm01.springboot.service;

import com.funix.asm01.springboot.entity.Donation;

import java.util.List;

public interface DonationService {
    public List<Donation> getDonations();//get list donation
    public void saveDonation(Donation donation);// save donation
    public Donation getDonation(long donationId);// get donation by ID
    public void changeDonationStatus(long donationId, long status);// change status of donation

    public void deleteDonation(long donationId);// delete donation in status 1

}
