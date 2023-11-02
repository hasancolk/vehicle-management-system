package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.dto.IndependentVehicleDto;
import com.hasancolk.vehiclemanagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    void deleteById(Long id);

    @Query(value = "select v from Vehicle v where v.groupId = :groupId")
    List<Vehicle>fetchVehiclesByGroupId(@Param(value = "groupId") Long groupId);

    @Query(value = "select new com.hasancolk.vehiclemanagement.dto.IndependentVehicleDto(v,uvp.role) " +
                   "from Vehicle  v, UserVehiclePermission uvp " +
                   "where v.id = uvp.vehicleId and uvp.userId=:userId")
    List<IndependentVehicleDto>fetchVehiclesByUserId(@Param(value= "userId") Long userId);

    @Query("select companyId from Vehicle where id = :vehicleId")
    Long findCompanyIdByVehicleId(Long vehicleId);

    List<Vehicle> findByCompanyId(Long companyId);

    @Query("select count(id) > 0 from Vehicle where id = :vehicleId and companyId = :companyId")
    boolean existsByVehicleIdAndCompanyId(@Param("vehicleId") Long vehicleId, @Param("companyId") Long companyId);

}