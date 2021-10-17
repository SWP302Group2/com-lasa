package com.lasa.data.customrepository;

import java.util.List;

public interface FavoriteLecturerCustomRepository {

    List<Integer> findTopFavoriteLecturerId(Integer topNumber);
}
