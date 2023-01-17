package com.ahmetdayi.ticketapp.repository.service;


import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.VehicleDoesntExistException;
import com.ahmetdayi.ticketapp.entity.Vehicle;
import com.ahmetdayi.ticketapp.entity.converter.VehicleConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.request.UpdateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.response.VehicleResponse;
import com.ahmetdayi.ticketapp.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final VehicleConverter vehicleConverter;

    public VehicleResponse create(CreateVehicleRequest request){

        Vehicle vehicle = new Vehicle(request.getSeatCount());

        return vehicleConverter.convert(vehicleRepository.save(vehicle));
    }

    public VehicleResponse updateSeatCount(UpdateVehicleRequest request){
        Vehicle vehicle = findById(request.getId());
        vehicle.setSeatCount(request.getSeatCount());
        return vehicleConverter.convert(vehicleRepository.save(vehicle));
    }

    public void deleteById(int id){
        vehicleRepository.deleteById(findById(id).getId());
    }

    protected Vehicle findById(int id){
        return vehicleRepository.findById(id).orElseThrow(()-> new VehicleDoesntExistException(Constant.VEHICLE_DOESNT_EXIST));
    }
}
