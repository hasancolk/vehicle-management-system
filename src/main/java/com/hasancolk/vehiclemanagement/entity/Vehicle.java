package com.hasancolk.vehiclemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle", schema = "public")
public class Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "vehicle_seq_gen")
    @SequenceGenerator(name = "vehicle_seq_gen",
            sequenceName = "vehicle_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "plate")
    private String plate;

    @Column(name = "chassis_no")
    private String chassisNo;

    @Column(name = "label")
    private String label;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "company_id")
    private Long companyId;

}
