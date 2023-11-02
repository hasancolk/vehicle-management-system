package com.hasancolk.vehiclemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group", schema = "public")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "group_seq_gen")
    @SequenceGenerator(name = "group_seq_gen",
            sequenceName = "group_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_group_id")
    private Long parentGroupId;

    @Column(name = "company_id")
    private Long companyId;

}
