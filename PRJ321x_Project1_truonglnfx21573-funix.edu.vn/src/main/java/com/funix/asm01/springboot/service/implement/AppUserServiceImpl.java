package com.funix.asm01.springboot.service.implement;

import com.funix.asm01.springboot.entity.AppUser;
import com.funix.asm01.springboot.repository.AppUserRepository;
import com.funix.asm01.springboot.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getAppUser(long userId) {
        Optional<AppUser> optional= appUserRepository.findById(userId);
        AppUser user =null;
        if (optional.isPresent()){
            user=optional.get();
        }else {
            throw new RuntimeException("Not found user by userId:"+userId);
        }
        return user;
    }

    @Override
    public void saveAppUser(AppUser user) {
        this.appUserRepository.save(user);
    }

    @Override
    public void lockAppUser(long id) {
        this.appUserRepository.lockAccount(id);
    }

    @Override
    public void unlockAppUser(long id) {
        this.appUserRepository.unlockAccount(id);
    }

    @Override
    public void deleteAppUser(long id) {
        this.appUserRepository.deleteAccount(id);
    }

    @Override
    public void updateAppUser(long userId, String fullName, String address, String phoneNumber, long roleId) {
        this.appUserRepository.updateAccount(userId,fullName,address,phoneNumber,roleId);
    }
}
