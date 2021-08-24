package id.example.javaSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.example.javaSpring.model.entity.District;
import id.example.javaSpring.model.entity.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {
    @Query(value = "select village.* from village join district on village.district_id = district.id where district.id = ?", nativeQuery = true)
    List<Village> findAllVillage(Integer id);

    List<Village> findByIsDeleted(boolean status);

    List<Village> findByDistrict(District district);

}