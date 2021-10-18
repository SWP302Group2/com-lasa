package com.lasa.data.repo.customrepository.impl;

import com.lasa.data.repo.customrepository.FavoriteLecturerCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FavoriteLecturerCustomRepositoryImpl implements FavoriteLecturerCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Integer> findTopFavoriteLecturerId(Integer topNumber) {
        return entityManager.createQuery("SELECT f.lecturer.id FROM FavoriteLecturer f GROUP BY f.lecturer.id order by count(f.lecturer.id) desc ", Integer.class)
                .setMaxResults(topNumber)
                .getResultList();
    }
}
