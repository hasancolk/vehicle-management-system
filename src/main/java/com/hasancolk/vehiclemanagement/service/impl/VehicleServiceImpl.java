package com.hasancolk.vehiclemanagement.service.impl;

import com.hasancolk.vehiclemanagement.request.CreateVehicleRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleDetailsRequest;
import com.hasancolk.vehiclemanagement.request.UpdateVehicleGroupRequest;
import com.hasancolk.vehiclemanagement.entity.Vehicle;
import com.hasancolk.vehiclemanagement.repository.VehicleRepository;
import com.hasancolk.vehiclemanagement.exception.EntityNotFoundException;
import com.hasancolk.vehiclemanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public Vehicle createVehicle(CreateVehicleRequest createVehicleRequest, Long companyId) {

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate(createVehicleRequest.getPlate());
        vehicle.setChassisNo(createVehicleRequest.getChassisNo());
        vehicle.setLabel(createVehicleRequest.getLabel());
        vehicle.setBrand(createVehicleRequest.getBrand());
        vehicle.setModel(createVehicleRequest.getModel());
        vehicle.setModelYear(createVehicleRequest.getModelYear());
        vehicle.setCompanyId(companyId);

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleDetails(UpdateVehicleDetailsRequest updateVehicleDetailsRequest) {

        Long vehicleId = updateVehicleDetailsRequest.getVehicleId();
        String plate = updateVehicleDetailsRequest.getPlate();
        String chassisNo = updateVehicleDetailsRequest.getChassisNo();
        String label = updateVehicleDetailsRequest.getLabel();
        String brand = updateVehicleDetailsRequest.getBrand();
        String model = updateVehicleDetailsRequest.getModel();
        Integer modelYear = updateVehicleDetailsRequest.getModelYear();

        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new EntityNotFoundException("The vehicle was not found!");
        }

        if(StringUtils.hasText(plate)){
            vehicle.get().setPlate(plate);
        }
        if(StringUtils.hasText(chassisNo)){
            vehicle.get().setChassisNo(chassisNo);
        }
        if(StringUtils.hasText(label)){
            vehicle.get().setLabel(label);
        }
        if(StringUtils.hasText(brand)){
            vehicle.get().setBrand(brand);
        }
        if(StringUtils.hasText(model)){
            vehicle.get().setModel(model);
        }
        if(modelYear != null){
            vehicle.get().setModelYear(modelYear);
        }
        return vehicleRepository.save(vehicle.get());
    }

    @Override
    public Vehicle updateVehicleGroup(UpdateVehicleGroupRequest updateVehicleGroupRequest) {

        Optional<Vehicle> vehicle = vehicleRepository.findById(updateVehicleGroupRequest.getVehicleId());

        if(vehicle.isEmpty()){
            throw new EntityNotFoundException("The vehicle was not found!");
        }

        vehicle.get().setGroupId(updateVehicleGroupRequest.getGroupId());
        return vehicleRepository.save(vehicle.get());
    }

    @Override
    public void deleteVehicle(Long userId, Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public Vehicle fetchVehicleById(Long vehicleId) {

        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        if(vehicle.isEmpty()){
            throw new EntityNotFoundException("Vehicle was not found!");
        }

        return vehicle.get();
    }

    @Override
    public List<Vehicle> fetchAllCompanyVehiclesByCompanyId(Long companyId) {
        return vehicleRepository.findByCompanyId(companyId);
    }

}