package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.dto.UserVehiclePermissionDto;
import com.hasancolk.vehiclemanagement.entity.UserVehiclePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserVehiclePermissionRepository extends JpaRepository<UserVehiclePermission, Long> {

    @Query("select count(id) > 0 from UserVehiclePermission where userId = :userId and vehicleId = :vehicleId and role = 1")
    boolean isVehicleAdminByUserIdAndVehicleId(@Param("userId") Long userId,@Param("vehicleId") Long vehicleId);

    boolean existsByUserIdAndVehicleId(Long userId,Long vehicleId);

    UserVehiclePermission findByUserIdAndVehicleId(Long userId, Long vehicleId);

    void deleteByUserIdAndVehicleId(Long userId,Long vehicleId);

    @Query(value = "select new com.hasancolk.vehiclemanagement.dto.UserVehiclePermissionDto(u.id,u.username,uvp.role) " +
                   "from UserVehiclePermission uvp, User u " +
                   "where uvp.userId = u.id and uvp.vehicleId = :vehicleId")
    List<UserVehiclePermissionDto> findByVehicleId(@Param("vehicleId") Long vehicleId);

}