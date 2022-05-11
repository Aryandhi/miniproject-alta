package com.alta.miniprojectcheckpoint.repository;

import com.alta.miniprojectcheckpoint.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<String, Integer> {
//    @Query("SELECT d FROM Users d WHERE d.name = :name")
//    Users finddByName(@Param("name")String name);
    //    untuk mengambil data pertama berdasarkan username
    Users getDistinctTopByUsername(String username);
}
