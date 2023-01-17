package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.Vehicle;
import com.ahmetdayi.ticketapp.entity.response.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleConverter {

    public VehicleResponse convert(Vehicle from){
        if (from==null){
            return null;
        }
        return new VehicleResponse(from.getId(), from.getSeatCount());
    }
}
