package com.hasancolk.vehiclemanagement.entity;

import com.hasancolk.vehiclemanagement.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_vehicle_permission", schema = "public")
public class UserVehiclePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_vehicle_permission_seq_gen")
    @SequenceGenerator(name = "user_vehicle_permission_seq_gen",
            sequenceName = "user_vehicle_permission_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "role_id")
    private Role role;

}
