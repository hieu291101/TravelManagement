package com.lth.repository;

import com.lth.pojos.Province;

import java.util.List;

public interface ProvinceRepository {
    List<Province> getProvinces(String name);
    Province findProvinceById(int id);
    boolean updateProvince(Province province);

}
