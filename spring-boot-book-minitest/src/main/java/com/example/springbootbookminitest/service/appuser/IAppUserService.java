package com.example.springbootbookminitest.service.appuser;

import com.example.springbootbookminitest.model.AppUser;
import com.example.springbootbookminitest.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<AppUser>, UserDetailsService {
}
