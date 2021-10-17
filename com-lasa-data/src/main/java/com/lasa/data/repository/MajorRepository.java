/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repository;

import com.lasa.data.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author hai
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, String>, JpaSpecificationExecutor<Major> {
    
    public void deleteByIdIn(List<String> ids);

    <T> List<T> findBy(Class<T> classType);
}
