package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.CityDoesntExistException;
import com.ahmetdayi.ticketapp.entity.City;
import com.ahmetdayi.ticketapp.entity.converter.CityConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateCityRequest;
import com.ahmetdayi.ticketapp.entity.response.CityResponse;
import com.ahmetdayi.ticketapp.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final CityConverter cityConverter;

    public List<CityResponse> findAll(){
        return cityConverter.convert(cityRepository.findAll());
    }

    public CityResponse create(CreateCityRequest request){
        City city = new City(request.getName());
        return cityConverter.convert(cityRepository.save(city));
    }

    public void deleteById(int id){
        cityRepository.deleteById(findById(id).getId());
    }

    protected City findById(int id){
        return cityRepository.findById(id).orElseThrow(()->new CityDoesntExistException(Constant.CITY_DOESNT_EXIST));
    }
}
