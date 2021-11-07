/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Student;
import com.lasa.data.model.utils.specification.StudentSpecification;
import com.lasa.data.repo.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ASUS
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentSearchCriteriaTest {
    
    private final StudentRepository studentRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public StudentSearchCriteriaTest(StudentRepository studentRepository,TestEntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testStudentSearchCriteriaWithEmailArgument() {
        String searchEmail = "chauvdpse62163@fpt.edu.vn";
        StudentSearchCriteria searchCriteria = StudentSearchCriteria.builder()
                .email(searchEmail)
                .build();
        List<Student> actualList = studentRepository.findAll(StudentSpecification.searchSpecification(searchCriteria));

        List<Student> expectedList = entityManager.getEntityManager()
                .createQuery("select s from Student s where s.email like ?1", Student.class)
                .setParameter(1, "%" + searchEmail + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }

    @Test
    public void testStudentSearchCriteriaWithEmailAndNameArgument() {
        String searchEmail = "chau";
        String searchName = "Chau";
        StudentSearchCriteria searchCriteria = StudentSearchCriteria.builder()
                .email(searchEmail)
                .name(searchName)
                .build();

        List<Student> actualList = studentRepository.findAll(StudentSpecification.searchSpecification(searchCriteria));

        List<Student> expectedList = entityManager.getEntityManager()
                .createQuery("select s from Student s where s.email like ?1 and s.name like ?2", Student.class)
                .setParameter(1, "%" + searchEmail + "%")
                .setParameter(2, "%" + searchName + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }
    
     @Test
    public void testStudentSearchCriteriaWithMssvArgument() {
        String searchMssv = "SE62163";
        StudentSearchCriteria searchCriteria = StudentSearchCriteria.builder()
                .mssv(searchMssv)
                .build();
        List<Student> actualList = studentRepository.findAll(StudentSpecification.searchSpecification(searchCriteria));

        List<Student> expectedList = entityManager.getEntityManager()
                .createQuery("select s from Student s where s.mssv like ?1", Student.class)
                .setParameter(1, "%" + searchMssv + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
    
}
