package com.hasancolk.vehiclemanagement.service;

import com.hasancolk.vehiclemanagement.request.UpdateParentGroupRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleGroupRequest;

public interface CheckService {

    boolean checkCreateGroupPermission(Long userId, Long parentGroupId);
    boolean checkUpdateParentGroupPermission(Long userId, UpdateParentGroupRequest updateParentGroupRequest);
    boolean checkUserGroupAdminPermission(Long userId, Long groupId);
    boolean checkUserGroupStandardOrAdminPermission(Long userId, Long groupId);
    boolean checkUserVehicleAdminPermission(Long userId, Long vehicleId);
    boolean checkUserVehicleStandardOrAdminPermission(Long userId, Long vehicleId);
    boolean checkUpdateVehicleGroupPermission(Long userId, UpdateVehicleGroupRequest updateVehicleGroupRequest);
    boolean checkCompanyAdminByUserIdAndCompanyId(Long userId, Long companyId);
    boolean checkCompanyAdminByUserId(Long userId);
    void checkUserIsNotInCompany(Long userId);
    boolean checkUserExistsInCompany(Long userId, Long companyId);
    void checkUserIsNotCompanyAdmin(Long userId, Long companyId);
    void checkUserIsNotAuthorizedInGroup(Long userId, Long groupId);
    void checkUserIsNotAuthorizedInVehicle(Long userId, Long vehicleId);
    void checkUserGroupPermissionExists(Long userId, Long groupId);
    void checkUserVehiclePermissionExists(Long userId, Long vehicleId);

}
