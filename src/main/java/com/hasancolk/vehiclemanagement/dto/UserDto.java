package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.entity.User;
import com.hasancolk.vehiclemanagement.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private Long companyId;
    private Role userCompanyRole;

    public UserDto(Long id, String username, String name, String surname, Long companyId, Role userCompanyRole) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.companyId = companyId;
        this.userCompanyRole = userCompanyRole;
    }

    public UserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.companyId = user.getCompanyId();
        this.userCompanyRole = user.getRole();
    }

}
