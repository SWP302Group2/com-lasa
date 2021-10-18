package com.lasa.data.repo.customrepository.impl;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.repo.customrepository.FavoriteLecturerCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Lecturer> findTopFavoriteLecturer(Integer topNumber) {
        List<Tuple> tuples =  entityManager
                .createQuery("SELECT f.lecturer.id as id, lec.name as name, lec.avatarUrl as avatar, lec.email as email, lec.gender as gender, lec.phone as phone  " +
                        "FROM FavoriteLecturer f JOIN f.lecturer as lec " +
                        "GROUP BY f.lecturer.id, lec.name, lec.avatarUrl, lec.email, lec.gender, lec.phone " +
                        "ORDER BY count(f.lecturer.id) desc ", Tuple.class)
                .setMaxResults(topNumber)
                .getResultList();
        List<Lecturer> lecturers = tuples.stream().map(
                tuple -> {
                    Lecturer lecturer = Lecturer.builder()
                            .id((Integer) tuple.get("id"))
                            .name((String) tuple.get("name"))
                            .avatarUrl((String) tuple.get("avatar"))
                            .email((String) tuple.get("email"))
                            .gender((Boolean) tuple.get("gender"))
                            .phone((String) tuple.get("phone"))
                            .build();
                    return lecturer;
                }
        ).collect(Collectors.toList());

        return lecturers;
    }
}
