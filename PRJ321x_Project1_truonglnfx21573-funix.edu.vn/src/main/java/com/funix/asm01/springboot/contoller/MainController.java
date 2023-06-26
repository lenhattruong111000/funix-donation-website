package com.funix.asm01.springboot.contoller;

import com.funix.asm01.springboot.entity.Donation;
import com.funix.asm01.springboot.entity.UserDonation;
import com.funix.asm01.springboot.service.AppUserService;
import com.funix.asm01.springboot.service.DonationService;
import com.funix.asm01.springboot.service.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MainController {
    @Autowired
    private DonationService donationService;
    @Autowired
    private UserDonationService userDonationService;
    @Autowired
    private AppUserService appUserService;

    private final long STATUS_1_WAITING_FOR_VERIFICATION = 1;
    private final long STATUS_2_VERIFICATION_FOR_USER_DONATION = 2;

    @RequestMapping("/")
    public String showHomePage(Model model){
        model.addAttribute("donations",this.donationService.getDonations());
        model.addAttribute("userDonation", new UserDonation());
        model.addAttribute("userDonationList", userDonationService.getUserDonations());
        return "public/home";
    }

    @PostMapping("user/save/userDonation")
    public String saveUserDonation(@ModelAttribute("donation")Donation donation,
                                   @ModelAttribute("userDonation") UserDonation userDonation,
                                   @RequestParam("idUser") long userId){
        System.out.println(donation.getId());
        System.out.println(userDonation.getName());
        System.out.println(userDonation.getMoney());
        System.out.println(userDonation.getText());
        System.out.println(userId);

        userDonation.setDateDonation(Date.valueOf(java.time.LocalDate.now()));
        userDonation.setDonation(this.donationService.getDonation(donation.getId()));
        userDonation.setUser(this.appUserService.getAppUser(userId));
        userDonation.setStatus(STATUS_1_WAITING_FOR_VERIFICATION);
        userDonation.setCreated("create successful");

        //create new userDonation
        UserDonation newUserDonation = new UserDonation();
        newUserDonation.setDonation(this.donationService.getDonation(donation.getId()));
        newUserDonation.setName(userDonation.getName());
        newUserDonation.setMoney(userDonation.getMoney());
        newUserDonation.setText(userDonation.getText());
        newUserDonation.setDateDonation(userDonation.getDateDonation());
        newUserDonation.setDonation(userDonation.getDonation());
        newUserDonation.setUser(userDonation.getUser());
        newUserDonation.setStatus(STATUS_1_WAITING_FOR_VERIFICATION);
        newUserDonation.setCreated(userDonation.getCreated());
        //store to database
        this.userDonationService.saveUserDonation(newUserDonation);

        return "redirect:/";
    }
}
