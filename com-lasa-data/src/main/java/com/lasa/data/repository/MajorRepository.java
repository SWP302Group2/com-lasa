/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import java.util.List;

import com.lasa.data.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hai
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, String>{
    
    public void deleteByIdIn(List<String> ids);
}
