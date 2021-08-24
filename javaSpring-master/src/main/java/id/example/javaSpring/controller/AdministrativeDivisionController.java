package id.example.javaSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.example.javaSpring.model.dto.AdministrativeDivisionDto;
import id.example.javaSpring.model.entity.City;
import id.example.javaSpring.model.entity.District;
import id.example.javaSpring.model.entity.Province;
import id.example.javaSpring.model.entity.Village;
import id.example.javaSpring.repository.CityRepository;
import id.example.javaSpring.repository.DistrictRepository;
import id.example.javaSpring.repository.ProvinceRepository;
import id.example.javaSpring.repository.VillageRepository;

@RestController
@RequestMapping("/division")
public class AdministrativeDivisionController {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private VillageRepository villageRepository;

    // ==================================================================================================================================
    // PROVINCE - PROVINSI

    // Insert province
    @PostMapping("/insertprovince")
    public ResponseEntity<?> insertProvince(@RequestBody AdministrativeDivisionDto dto) {
        Province province = new Province(dto.getProvinceCode(), dto.getProvinceName());
        if (dto.getProvinceCode() == province.getProvinceCode()) {
        }
        provinceRepository.save(province);
        return ResponseEntity.ok().body(province);
    }

    // Find all province
    @GetMapping("/allprovince")
    public ResponseEntity<?> getProvince() {
        List<Province> provinces = provinceRepository.findAll();
        return ResponseEntity.ok().body(provinces);
    }

    // Find all active province
    @GetMapping("/allactiveprovince")
    public ResponseEntity<?> getActiveProvince() {
        List<Province> provinces = provinceRepository.findByIsDeleted(false);
        return ResponseEntity.ok().body(provinces);
    }

    // Find province by provinceId
    @GetMapping("province/{id}")
    public ResponseEntity<?> getProvinceByProvinceId(@PathVariable Integer id) {
        if (provinceRepository.findById(id).isPresent()) {
            Province province = provinceRepository.findById(id).get();
            return ResponseEntity.ok().body(province);
        }
        return ResponseEntity.badRequest().body("ProvinceId not found");
    }

    // Update province by provinceId
    @PutMapping("/province/update/{id}")
    public ResponseEntity<?> updateProvince(@RequestBody AdministrativeDivisionDto dto, @PathVariable Integer id) {
        Province province = provinceRepository.findById(id).get();
        province.setProvinceCode(dto.getProvinceCode());
        province.setProvinceName(dto.getProvinceName());
        provinceRepository.save(province);
        return ResponseEntity.ok().body(province);
    }

    // Delete province - soft delete
    @DeleteMapping("/province/delete/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable Integer id) {
        Province province = provinceRepository.findById(id).get();
        province.setDeleted(true);
        provinceRepository.save(province);
        return ResponseEntity.ok().body(province);
    }

    // ==================================================================================================================================
    // CITY - KOTA / KABUPATEN

    // Insert city
    @PostMapping("/insertcity")
    public ResponseEntity<?> insertCity(@RequestBody AdministrativeDivisionDto dto) {
        City city = new City(dto.getCityCode(), dto.getCityName());
        Province province = provinceRepository.findByProvinceName(dto.getProvinceName());
        city.setProvince(province);
        cityRepository.save(city);
        return ResponseEntity.ok().body(city);
    }

    // Find all city
    @GetMapping("/allcity")
    public ResponseEntity<?> getCity() {
        List<City> cities = cityRepository.findAll();
        return ResponseEntity.ok().body(cities);
    }

    // Find all active city
    @GetMapping("/allactivecity")
    public ResponseEntity<?> getActiveCity() {
        List<City> cities = cityRepository.findByIsDeleted(false);
        return ResponseEntity.ok().body(cities);
    }

    // Find city by cityId
    @GetMapping("city/{id}")
    public ResponseEntity<?> getCityByCityId(@PathVariable Integer id) {
        if (cityRepository.findById(id).isPresent()) {
            City city = cityRepository.findById(id).get();
            return ResponseEntity.ok().body(city);
        }
        return ResponseEntity.badRequest().body("cityId not found");
    }

    // Find city by provinceid
    @GetMapping("citybyprovince/{id}")
    public ResponseEntity<?> getCityByProvinceId(@PathVariable Integer id) {
        Province province = provinceRepository.findById(id).get();
        List<City> cities = cityRepository.findByProvince(province);
        return ResponseEntity.ok().body(cities);
    }

    // Find city by provinceId
    // @GetMapping("/citybyprovince/{id}")
    // public ResponseEntity<?> getCityByProvinceId(@PathVariable Integer id) {
    // List<City> cities = cityRepository.findByProvinceId(id);
    // return ResponseEntity.ok().body(cities);
    // }

    // Update city by cityId
    @PutMapping("/city/update/{id}")
    public ResponseEntity<?> updateCity(@RequestBody AdministrativeDivisionDto dto, @PathVariable Integer id) {
        City city = cityRepository.findById(id).get();
        city.setCityCode(dto.getCityCode());
        city.setCityName(dto.getCityName());
        cityRepository.save(city);
        return ResponseEntity.ok().body(city);
    }

    // Delete city - soft delete
    @DeleteMapping("/city/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        City city = cityRepository.findById(id).get();
        city.setDeleted(true);
        cityRepository.save(city);
        return ResponseEntity.ok().body(city);
    }

    // ==================================================================================================================================
    // DISTRICT - KECAMATAN

    // Insert district
    @PostMapping("/insertdistrict")
    public ResponseEntity<?> insertDistrict(@RequestBody AdministrativeDivisionDto dto) {
        District district = new District(dto.getDistrictCode(), dto.getDistrictName());
        City city = cityRepository.findByCityName(dto.getCityName());
        district.setCity(city);
        districtRepository.save(district);
        return ResponseEntity.ok().body(district);
    }

    // Find all district
    @GetMapping("/alldistrict")
    public ResponseEntity<?> getDistrict() {
        List<District> districts = districtRepository.findAll();
        return ResponseEntity.ok().body(districts);
    }

    // Find all active district
    @GetMapping("/allactivedistrict")
    public ResponseEntity<?> getActiveDistrict() {
        List<District> districts = districtRepository.findByIsDeleted(false);
        return ResponseEntity.ok().body(districts);
    }

    // Find district by districtId
    @GetMapping("district/{id}")
    public ResponseEntity<?> getDistrictByDistrictId(@PathVariable Integer id) {
        if (districtRepository.findById(id).isPresent()) {
            District district = districtRepository.findById(id).get();
            return ResponseEntity.ok().body(district);
        }
        return ResponseEntity.badRequest().body("districtId not found");
    }

    // Find district by cityId
    @GetMapping("districtbycity/{id}")
    public ResponseEntity<?> getDistrictByCityId(@PathVariable Integer id) {
        City city = cityRepository.findById(id).get();
        List<District> district = districtRepository.findByCity(city);
        return ResponseEntity.ok().body(district);
    }

    // Update district by districtId
    @PutMapping("/district/update/{id}")
    public ResponseEntity<?> updateDistrict(@RequestBody AdministrativeDivisionDto dto, @PathVariable Integer id) {
        District district = districtRepository.findById(id).get();
        district.setDistrictCode(dto.getDistrictCode());
        district.setDistrictName(dto.getDistrictName());
        districtRepository.save(district);
        return ResponseEntity.ok().body(district);
    }

    // Delete district - soft delete
    @DeleteMapping("/district/delete/{id}")
    public ResponseEntity<?> deleteDistrict(@PathVariable Integer id) {
        District district = districtRepository.findById(id).get();
        district.setDeleted(true);
        districtRepository.save(district);
        return ResponseEntity.ok().body(district);
    }

    // ==================================================================================================================================
    // VILLAGE - KELURAHAN / DESA

    // Insert village
    @PostMapping("/insertvillage")
    public ResponseEntity<?> insertVillage(@RequestBody AdministrativeDivisionDto dto) {
        Village village = new Village(dto.getVillageCode(), dto.getVillageName());
        District district = districtRepository.findByDistrictName(dto.getDistrictName());
        village.setDistrict(district);
        villageRepository.save(village);
        return ResponseEntity.ok().body(village);
    }

    // Find all village
    @GetMapping("/allvillage")
    public ResponseEntity<?> getVillage() {
        List<Village> villages = villageRepository.findAll();
        return ResponseEntity.ok().body(villages);
    }

    // Find all active district
    @GetMapping("/allactivevillage")
    public ResponseEntity<?> getActiveVillage() {
        List<Village> villages = villageRepository.findByIsDeleted(false);
        return ResponseEntity.ok().body(villages);
    }

    // Find village by villageId
    @GetMapping("village/{id}")
    public ResponseEntity<?> getVillageByVillageId(@PathVariable Integer id) {
        if (villageRepository.findById(id).isPresent()) {
            Village village = villageRepository.findById(id).get();
            return ResponseEntity.ok().body(village);
        }
        return ResponseEntity.badRequest().body("villageId not found");
    }

    // Find village by districtId
    @GetMapping("villagebydistrict/{id}")
    public ResponseEntity<?> getVillageByDistrictId(@PathVariable Integer id) {
        District district = districtRepository.findById(id).get();
        List<Village> villages = villageRepository.findByDistrict(district);
        return ResponseEntity.ok().body(villages);
    }

    // Update village by villageId
    @PutMapping("/village/update/{id}")
    public ResponseEntity<?> updateVillage(@RequestBody AdministrativeDivisionDto dto, @PathVariable Integer id) {
        Village village = villageRepository.findById(id).get();
        village.setVillageCode(dto.getVillageCode());
        village.setVillageName(dto.getVillageName());
        villageRepository.save(village);
        return ResponseEntity.ok().body(village);
    }

    // Delete village - soft delete
    @DeleteMapping("/village/delete/{id}")
    public ResponseEntity<?> deleteVillage(@PathVariable Integer id) {
        Village village = villageRepository.findById(id).get();
        village.setDeleted(true);
        villageRepository.save(village);
        return ResponseEntity.ok().body(village);
    }

}
