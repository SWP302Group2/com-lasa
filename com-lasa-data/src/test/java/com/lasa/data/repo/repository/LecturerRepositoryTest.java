
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.Lecturer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


/**
 *
 * @author MyNhung
 */
@DataJpaTest
@TestMethodOrder(Alphanumeric.class)
public class LecturerRepositoryTest {
    
    @Autowired
    LecturerRepository lecturerRepository;
    
    public LecturerRepositoryTest() {
    }

    @Test
    public void testcase_01_FindById() {
                 
        Integer ExpectedId = 1;
        String ExpectedEmail = "khiemtd@fe.edu.vn";
        String ExpectedName = "Tran Duy Khiem";
    
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getId(),ExpectedId); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getEmail(),ExpectedEmail); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getName(),ExpectedName);
        
    }
    
    @Test
    public void testcase_02_UpdateLecturer()
    {
        Integer ExpectedId = 2;
        String  ExpectedEmail = "testUpdate@fe.edu.vn";
        String ExpectedName = "testUpdate";
        
        Lecturer lecture = new Lecturer();
        lecture.setId(ExpectedId);
        lecture.setEmail(ExpectedEmail);
        lecture.setName(ExpectedName);
        
     
        lecturerRepository.save(lecture);
        
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getId(),ExpectedId); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getEmail(),ExpectedEmail); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getName(),ExpectedName);
    }
    
    @Test
    @Rollback(false)
    public void testcase_03_CreateLecturer()
    {
        Integer ExpectedId = 44;
        String  ExpectedEmail = "NewLecturer@fe.edu.vn";
        String ExpectedName = "NewLecturer";
        
        Lecturer lecture = new Lecturer();
        lecture.setId(ExpectedId);
        lecture.setEmail(ExpectedEmail);
        lecture.setName(ExpectedName);
        
        lecturerRepository.save(lecture);
        
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getId(),ExpectedId); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getEmail(),ExpectedEmail); 
        Assertions.assertEquals(lecturerRepository.getById(ExpectedId).getName(),ExpectedName);
    }
    
    @Test
    @Rollback(false)
    public void testcase_04_DeleteLecturer()
    {
        Integer ExpectedId = 44;
        
        lecturerRepository.deleteById(ExpectedId);
        boolean IsLectureExist = lecturerRepository.existsById(ExpectedId);
        
        Assertions.assertEquals(IsLectureExist,false);
    }
}