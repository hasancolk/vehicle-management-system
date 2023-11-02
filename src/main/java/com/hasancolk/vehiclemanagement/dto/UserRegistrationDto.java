package com.hasancolk.vehiclemanagement.dto;

import com.hasancolk.vehiclemanagement.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private Long id;
    private String username;
    private String name;
    private String surname;

    public UserRegistrationDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
    }

}
