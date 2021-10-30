package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.utils.specification.LecturerSpecification;
import com.lasa.data.repo.repository.LecturerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LecturerSearchCriteriaTest {

    private final LecturerRepository lecturerRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public LecturerSearchCriteriaTest(LecturerRepository lecturerRepository,TestEntityManager entityManager) {
        this.lecturerRepository = lecturerRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testLecturerSearchCriteriaWithEmailArgument() {
        String searchEmail = "khiem";
        LecturerSearchCriteria searchCriteria = LecturerSearchCriteria.builder()
                .email(searchEmail)
                .build();

        List<Lecturer> actualList = lecturerRepository.findAll(LecturerSpecification.searchSpecification(searchCriteria));

        List<Lecturer> expectedList = entityManager.getEntityManager()
                .createQuery("select l from Lecturer l where l.email like ?1", Lecturer.class)
                .setParameter(1, "%" + searchEmail + "%")
                .getResultList();
        System.out.println(expectedList.size());
        Assertions.assertEquals(expectedList, actualList);

    }

    @Test
    public void testLecturerSearchCriteriaWithEmailAndNameArgument() {
        String searchEmail = "kh";
        String searchName = "a";
        LecturerSearchCriteria searchCriteria = LecturerSearchCriteria.builder()
                .email(searchEmail)
                .name(searchName)
                .build();

        List<Lecturer> expectedList = entityManager.getEntityManager()
                .createQuery("select l from Lecturer l where l.email like ?1 and l.name like ?2", Lecturer.class)
                .setParameter(1, "%" + searchEmail + "%")
                .setParameter(2, "%" + searchName + "%")
                .getResultList();
    }

}
