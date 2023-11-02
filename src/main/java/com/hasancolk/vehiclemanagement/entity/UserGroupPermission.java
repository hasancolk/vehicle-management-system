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
@Table(name = "user_group_permission", schema = "public")
public class UserGroupPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_group_permission_seq_gen")
    @SequenceGenerator(name = "user_group_permission_seq_gen",
            sequenceName = "user_group_permission_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "role_id")
    private Role role;

}
