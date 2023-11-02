package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.dto.GroupPermissionDto;
import com.hasancolk.vehiclemanagement.dto.UserGroupPermissionRoleDto;
import com.hasancolk.vehiclemanagement.entity.UserGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupPermissionRepository extends JpaRepository<UserGroupPermission, Long> {

    @Query(value = "SELECT groupId " +
                   "FROM UserGroupPermission " +
                   "WHERE userId = :userId")
    List<Long> findAuthorizedGroupIdListByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT new com.hasancolk.vehiclemanagement.dto.GroupPermissionDto(g.id,g.name,ugp.role) " +
                   "FROM UserGroupPermission ugp, Group g " +
                   "WHERE g.id = ugp.groupId and ugp.userId = :userId")
    List<GroupPermissionDto> fetchUserGroupPermissionsByUserId(@Param("userId") Long userId);

    @Query("SELECT count(g.id) > 0 " +
            "FROM Group g, UserGroupPermission ugp " +
            "WHERE ugp.userId = :userId and ugp.groupId = :groupId and ugp.role = 1")
    boolean isDirectGroupAdmin(Long userId, Long groupId);

    boolean existsByUserIdAndGroupId(Long userId, Long groupId);

    void deleteByUserIdAndGroupId(Long userId, Long groupId);

    UserGroupPermission findByUserIdAndGroupId(Long userId, Long groupId);

    @Query(value = "select new com.hasancolk.vehiclemanagement.dto.UserGroupPermissionRoleDto(u.id,u.username,ugp.role) " +
                   "from UserGroupPermission ugp, User u " +
                   "where ugp.userId = u.id and ugp.groupId = :groupId")
    List<UserGroupPermissionRoleDto> findByGroupId(@Param("groupId") Long groupId);

}