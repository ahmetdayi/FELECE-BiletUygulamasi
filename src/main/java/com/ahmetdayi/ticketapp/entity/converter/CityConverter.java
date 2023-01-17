package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.City;
import com.ahmetdayi.ticketapp.entity.response.CityResponse;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CityResponse convert(City from){
        if (from==null){
            return null;
        }
        return new CityResponse(from.getId(), from.getName());
    }
}
