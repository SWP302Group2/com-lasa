package com.lasa.data.repo.customrepository;

import java.util.List;

public interface FavoriteLecturerCustomRepository {

    List<Integer> findTopFavoriteLecturerId(Integer topNumber);
}
