package id.example.javaSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.example.javaSpring.model.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query(value = "select province.* from province where province.id = ?", nativeQuery = true)
    List<Province> findAllProvince(Integer id);

    List<Province> findByIsDeleted(boolean status);

    Province findByProvinceName(String provinceName);
}
