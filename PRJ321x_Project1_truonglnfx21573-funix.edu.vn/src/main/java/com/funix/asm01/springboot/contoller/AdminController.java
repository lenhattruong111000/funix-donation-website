package com.funix.asm01.springboot.contoller;

import com.funix.asm01.springboot.entity.AppUser;
import com.funix.asm01.springboot.entity.Donation;
import com.funix.asm01.springboot.entity.UserDonation;
import com.funix.asm01.springboot.service.AppUserService;
import com.funix.asm01.springboot.service.DonationService;
import com.funix.asm01.springboot.service.RoleService;
import com.funix.asm01.springboot.service.UserDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDonationService userDonationService;

    private final long STATUS_1_NEW_CREATED = 1;//"Mới Tạo"
    private final long STATUS_2_DONATING = 2;//"Đang quyên góp"
    private final long STATUS_3_END_OF_DONATION = 3;//"Kết thúc quyên góp"
    private final long STATUS_4_CLOSE_DONATION = 4;//"Đóng quyên góp"

    private final long STATUS_1_WAITING_FOR_VERIFICATION = 1;
    private final long STATUS_2_VERIFICATION_FOR_USER_DONATION = 2;

    private final long STATUS_3_CANCEL_VERIFICATION_FOR_USER_DONATION = 3;



    @Autowired
    private DonationService donationService;

    @RequestMapping("/login")
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/homepage")
    public String showHomePage(){
        return "admin/home";
    }

    @RequestMapping("/account")
    public  String getAccountsPage(Model model){
        //get account list from the database
        List<AppUser> appUsers = appUserService.getAppUsers();
        //appUsers.forEach(s-> System.out.println(s.getFullName()));
        model.addAttribute("appUsers",appUsers);
        return "admin/account";
    }

    @RequestMapping("/donation")
    public  String getDonationsPage(Model model){
        //create new donation
        Donation newDonation = new Donation();
        List<Donation> donations = this.donationService.getDonations();

        model.addAttribute("newDonation",newDonation);
        model.addAttribute("donations",donations);
        return "admin/donation";
    }
    @RequestMapping("/newAccount")
    public String getNewAccountForm(Model model){
        //create new user
        AppUser user = new AppUser();
        model.addAttribute("newAccount", user);
        return "admin/new-account-form";
    }

    //save new account to the database
    @PostMapping("saveNewAccount")
    public String saveNewAccount(@ModelAttribute("newAccount") AppUser user, @RequestParam("roleId") String roleId){
        //set role for user
        user.setRole(roleService.getRoleById(Long.parseLong(roleId)));
        //save user to database
        appUserService.saveAppUser(user);
        return "redirect:/admin/account";
    }

    // lock account function
    @GetMapping("status/inactive/{id}")
    public String lockAccount(@PathVariable(value = "id") long userId){
        this.appUserService.lockAppUser(userId);
        return "redirect:/admin/account";
    }

    // unlock account function
    @GetMapping("status/active/{id}")
    public String unlockAccount(@PathVariable(value = "id") long userId){
        this.appUserService.unlockAppUser(userId);
        return "redirect:/admin/account";
    }

    //delete account
    @GetMapping("delete/account/{id}")
    public String deleteAccount(@PathVariable(value = "id") long userId){
        //delete reference of user in user_donation table
        this.userDonationService.deleteUserDonation(userId);
        //delete user from user table
        this.appUserService.deleteAppUser(userId);
        return "redirect:/admin/account";

    }

    @PostMapping("account/update")
    public String saveEditAccount(@ModelAttribute("appUser") AppUser user, @RequestParam("roleId") String roleId){
        //user get from the update form
        user.setRole(roleService.getRoleById(Long.parseLong(roleId)));

        //current user
        AppUser currentUser = appUserService.getAppUser(user.getId());
        currentUser.setFullName(user.getFullName());
        currentUser.setRole(user.getRole());
        currentUser.setAddress(user.getAddress());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        //save update account
        this.appUserService.saveAppUser(currentUser);
//        this.appUserService.updateAppUser(user.getId(),user.getFullName(),
//                user.getAddress(), user.getPhoneNumber(), user.getRole().getId());
        return "redirect:/admin/account";
    }

    //start with donation

    //save new Donation
    @PostMapping("donation/saveNewDonation")
    public String saveNewDonation(@ModelAttribute("newDonation") Donation donation){
        donation.setCreated("Tạo thành công");
        donation.setStatus(STATUS_1_NEW_CREATED);
        donation.setMoney(0);
        this.donationService.saveDonation(donation);
        return "redirect:/admin/donation";
    }

    //update Donation
    @PostMapping("donation/update")
    public String updateDonation(@ModelAttribute("donation") Donation donation){
        Donation currDonation = donationService.getDonation(donation.getId());
        currDonation.setCode(donation.getCode());
        currDonation.setName(donation.getName());
        currDonation.setStartDate(donation.getStartDate());
        currDonation.setEndDate(donation.getEndDate());
        currDonation.setOrganizationName(donation.getOrganizationName());
        currDonation.setPhoneNumber(donation.getPhoneNumber());
        currDonation.setDescription(donation.getDescription());
        //update donation
        this.donationService.saveDonation(currDonation);
        return "redirect:/admin/donation";
    }

    //change donation status to status 2: donating
    @GetMapping("status/donation/{id}")
    public String startDonation(@PathVariable(value = "id") long donationId){
        this.donationService.changeDonationStatus(donationId,STATUS_2_DONATING);
        return "redirect:/admin/donation";
    }

    //change donation status to status 3: end of donation
    @GetMapping("status/endDonation/{id}")
    public String endDonation(@PathVariable(value = "id") long donationId){
        this.donationService.changeDonationStatus(donationId,STATUS_3_END_OF_DONATION);
        return "redirect:/admin/donation";
    }


    //delete donation in status 1(Mới tạo)
    @GetMapping("delete/donation/{id}")
    public String deleteDonation(@PathVariable("id")long donationId){
        this.donationService.deleteDonation(donationId);
        return "redirect:/admin/donation";
    }

    //get details of donation
    @GetMapping("donation/detail/{id}")
    public String detailDonation(@PathVariable(value = "id") long donationId, Model model){
        //get donation by id
        Donation donation = this.donationService.getDonation(donationId);
        //get user-donation information
        List<UserDonation> userDonations = this.userDonationService.getUserDonations(donationId);

        model.addAttribute("donationDetail",donation);
        model.addAttribute("userDonations",userDonations);
        return "admin/detail";
    }

    //verification donation
    @GetMapping("donation/detail/verification/accepted/{id}")
    public String acceptedVerification(@PathVariable("id") long userDonationId){
        UserDonation userDonation = userDonationService.getUserDonation(userDonationId);
        Donation donation = donationService.getDonation(userDonation.getDonation().getId());

        //update amount of money
        long totalMoney=donation.getMoney()+userDonation.getMoney();
        donation.setMoney(totalMoney);

        //update donation
        this.donationService.saveDonation(donation);

        //update status
        this.userDonationService.changeStatus(userDonationId,STATUS_2_VERIFICATION_FOR_USER_DONATION);
        return "redirect:/admin/donation/detail/"+ donation.getId();
    }
    @GetMapping("donation/detail/verification/rejected/{id}")
    public String rejectedVerification(@PathVariable("id") long userDonationId){
        UserDonation userDonation = userDonationService.getUserDonation(userDonationId);
        //update status
        this.userDonationService.changeStatus(userDonationId,STATUS_3_CANCEL_VERIFICATION_FOR_USER_DONATION);
        return "redirect:/admin/donation/detail/"+ userDonation.getDonation().getId();
    }
}
