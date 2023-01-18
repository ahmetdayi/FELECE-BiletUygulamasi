package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.City;
import com.ahmetdayi.ticketapp.entity.response.CityResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityConverter {

    public CityResponse convert(City from) {
        if (from == null) {
            return null;
        }
        return new CityResponse(from.getId(), from.getName());
    }

    public List<CityResponse> convert(List<City> fromList) {
        if (fromList == null) {
            return null;
        }

        return fromList
                .stream()
                .map
                        (
                                city -> new CityResponse
                                        (
                                                city.getId(),
                                                city.getName()
                                        )
                        ).collect(Collectors.toList());

    }
}
