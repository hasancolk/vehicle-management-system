package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.dto.UserDto;
import com.hasancolk.vehiclemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select id from User where username = :username")
    Long findUserIdByUsername(@Param("username") String username);

    @Query(value = "SELECT new com.hasancolk.vehiclemanagement.dto.UserDto(id,username,name,surname,companyId,role) " +
                   "FROM User " +
                   "WHERE companyId = :companyId")
    List<UserDto> findAllUsersByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT new com.hasancolk.vehiclemanagement.dto.UserDto(id,username,name,surname,companyId,role) " +
                   "FROM User " +
                   "WHERE id = :userId")
    UserDto findUserById(@Param("userId") Long userId);

    //////////

    boolean existsById(Long userId); // kontrol et

    boolean existsByIdAndCompanyIdIsNull(Long userId);

    boolean existsByIdAndCompanyId(Long userId, Long companyId);


    @Query("SELECT companyId FROM User WHERE id = :userId")
    Long findCompanyIdByUserId(@Param("userId") Long userId);

    @Query("select count(id) > 0 from User where id = :userId and role = 1")
    boolean isCompanyAdminByUserId(@Param("userId") Long userId);

    @Query("select count(id) > 0 from User where id = :userId and companyId = :companyId and role = 1")
    boolean isCompanyAdminByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);
}