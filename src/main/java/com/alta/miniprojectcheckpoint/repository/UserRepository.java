package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //    untuk mengambil data pertama berdasarkan username
    User getDistinctTopByUsername(String username);
}
