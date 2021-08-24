package id.example.javaSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.example.javaSpring.model.entity.City;
import id.example.javaSpring.model.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "select district.* from district join city on district.city_id = city.id where city.id = ?", nativeQuery = true)
    List<Object> findAllDistrict(Integer id);

    List<District> findByIsDeleted(boolean status);

    District findByDistrictName(String districtName);

    List<District> findByCity(City city);

}
