/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import com.lasa.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *
 * @author Admin
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findAdminByUsername(String username);

    @Query("SELECT a.id, a.name, a.username, a.avatarUrl, a.email, a.phone, a.gender, a.avatarUrl FROM Admin as a where a.id = :id")
    Optional<Admin> findAdminByIdWithoutPassword(@Param("id") Integer id);

}
