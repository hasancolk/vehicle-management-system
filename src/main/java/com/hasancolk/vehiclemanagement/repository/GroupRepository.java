package com.hasancolk.vehiclemanagement.repository;

import com.hasancolk.vehiclemanagement.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select companyId from Group where id = :groupId")
    Long findCompanyIdByGroupId(@Param("groupId") Long groupId);

    List<Group>findByParentGroupId(Long parentGroupId);

    @Query("select parentGroupId from Group where id = :groupId")
    Long getParentGroupIdByGroupId(@Param("groupId") Long groupId);

    @Query("select count(id) > 0 from Group where id = :groupId and companyId = :companyId")
    boolean existsByGroupIdAndCompanyId(@Param("groupId") Long groupId, @Param("companyId") Long companyId);

    @Query(value = "select id " +
                   "from Group " +
                   "where parentGroupId IS NULL and companyId = :companyId")
    List<Long> findAllParentGroupIdListByCompanyId(@Param("companyId") Long companyId);

}
