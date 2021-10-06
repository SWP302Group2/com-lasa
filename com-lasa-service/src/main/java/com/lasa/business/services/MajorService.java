/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Major;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface MajorService {
    
    public List<Major> findAll();
    
    public Major findById(String id);
    
    public List<Major> createMajors(List<Major> majors);
    
    public List<Major> updateMajors(List<Major> majors);
    
    public void deleteMajors(List<String> ids);
}
