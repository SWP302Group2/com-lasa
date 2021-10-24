package com.lasa.data.repo.customrepository;

import com.lasa.data.model.entity.Lecturer;

import java.util.List;

public interface FavoriteLecturerCustomRepository {

    List<Integer> findTopFavoriteLecturerId(Integer topNumber);

    List<Lecturer> findTopFavoriteLecturer(Integer topNumber);
}
