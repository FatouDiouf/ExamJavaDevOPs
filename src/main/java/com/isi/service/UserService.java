package com.isi.service;

import com.isi.dao.IUserRepository;
import com.isi.entities.CvUserEntity;
import com.isi.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserService implements UserDetailsService {
    private IUserRepository iUserRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public CvUserEntity save(CvUserEntity cvUserEntity) {
        cvUserEntity.setPassword(passwordEncoder.encode(cvUserEntity.getPassword()));
        cvUserEntity.setRoles( Arrays.asList(new Role("ROLE_USER")));

        return iUserRepository.save(cvUserEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CvUserEntity cvUserEntity = iUserRepository.findByEmail(username);
        if (cvUserEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(cvUserEntity.getEmail(), cvUserEntity.getPassword(), mapRolesToAuthorities(cvUserEntity.getRoles()));
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Role > roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNom())).collect(Collectors.toList());
    }
    public  CvUserEntity edit(int id) {return iUserRepository.findById(id).get();}

    public  CvUserEntity update(CvUserEntity cvEntity) {return iUserRepository.save(cvEntity);}

    public CvUserEntity getCV(String email) {return iUserRepository.findByEmail(email);}
}
