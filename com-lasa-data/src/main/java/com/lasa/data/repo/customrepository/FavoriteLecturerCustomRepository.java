package com.lasa.data.repo.customrepository;

import com.lasa.data.entity.FavoriteLecturer;
import com.lasa.data.entity.Lecturer;

import java.util.List;

public interface FavoriteLecturerCustomRepository {

    List<Integer> findTopFavoriteLecturerId(Integer topNumber);

    List<Lecturer> findTopFavoriteLecturer(Integer topNumber);
}
