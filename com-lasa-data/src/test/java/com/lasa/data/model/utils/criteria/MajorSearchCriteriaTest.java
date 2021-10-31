/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Major;
import com.lasa.data.model.utils.specification.MajorSpecification;
import com.lasa.data.repo.repository.MajorRepository;
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
 * @author MyNhung
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MajorSearchCriteriaTest {
    
    private final MajorRepository majorRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public MajorSearchCriteriaTest(MajorRepository majorRepository,TestEntityManager entityManager) {
        this.majorRepository = majorRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testMajorSearchCriteriaWithNameArgument() {
        String searchName = "SoftwareEngineering";
        MajorSearchCriteria searchCriteria = new MajorSearchCriteria();
        searchCriteria.setName(searchName);
                
        List<Major> actualList = majorRepository.findAll(MajorSpecification.searchSpecification(searchCriteria));

        List<Major> expectedList = entityManager.getEntityManager()
                .createQuery("select major from Major major where major.name like ?1", Major.class)
                .setParameter(1,  "%" + searchName+ "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
}
