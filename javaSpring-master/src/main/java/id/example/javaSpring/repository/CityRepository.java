package id.example.javaSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.example.javaSpring.model.entity.City;
import id.example.javaSpring.model.entity.Province;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value = "select city.* from city join province on city.province_id = province.id where province.id = ?", nativeQuery = true)
    List<City> findAllCity(Integer Id);

    List<City> findByIsDeleted(boolean status);

    City findByCityName(String cityName);

    List<City> findByProvince(Province province);

    // List<City> findByProvinceId(Integer Id);
}