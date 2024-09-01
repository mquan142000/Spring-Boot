package com.example.springbootbookminitest.service.approle;

import com.example.springbootbookminitest.model.AppRole;
import com.example.springbootbookminitest.repository.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService {

    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public Page<AppRole> findAll(Pageable pageable) {
        return appRoleRepository.findAll(pageable);
    }

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepository.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return appRoleRepository.findById(id);
    }

    @Override
    public void save(AppRole appRole) {
        appRoleRepository.save(appRole);
    }

    @Override
    public void remove(Long id) {
        appRoleRepository.deleteById(id);
    }
}
