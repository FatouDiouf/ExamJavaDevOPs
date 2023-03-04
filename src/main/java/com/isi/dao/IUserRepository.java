package com.isi.dao;

import com.isi.entities.CvUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<CvUserEntity, Integer> {
    CvUserEntity findByEmail(String email);
}
