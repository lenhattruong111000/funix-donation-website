package com.funix.asm01.springboot.service;

import com.funix.asm01.springboot.entity.AppUser;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserService {
    public List<AppUser> getAppUsers();// get account list
    public AppUser getAppUser(long userId);// get user by user id
    public void saveAppUser(AppUser user);
    public void lockAppUser(long id); //lock account
    public void unlockAppUser(long id); //unlock account

    public void deleteAppUser(long id); //delete account

    public void updateAppUser(long userId, String fullName,String address,
                              String phoneNumber, long roleId);//update account information
}
