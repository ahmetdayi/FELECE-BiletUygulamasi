package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.RouteDoesntExistException;
import com.ahmetdayi.ticketapp.entity.City;
import com.ahmetdayi.ticketapp.entity.Route;


import com.ahmetdayi.ticketapp.entity.converter.RouteConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateRouteRequest;
import com.ahmetdayi.ticketapp.entity.response.RouteResponse;
import com.ahmetdayi.ticketapp.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    //TODO email valid ıle password valid calımsıyor
    private final RouteRepository routeRepository;

    private final CityService cityService;

    private final RouteConverter routeConverter;

    public List<RouteResponse> getAll(){
        return routeConverter.convert(routeRepository.findAll());
    }

    public RouteResponse create(CreateRouteRequest request){
        City starting = cityService.findById(request.getStartingPointId());
        City ending = cityService.findById(request.getEndingPointId());

        Route route = new Route(starting,ending);
        return routeConverter.convert(routeRepository.save(route));
    }

    public RouteResponse update(int id ,int startingPointId,int endingPointId){
        Route route = findById(id);
        route.getEndingPoint().setId(cityService.findById(endingPointId).getId());
        route.getStartingPoint().setId(cityService.findById(startingPointId).getId());
        return routeConverter.convert(routeRepository.save(route));
    }

    public void deleteById(int id){
        routeRepository.deleteById(findById(id).getId());
    }

    protected Route findById(int id){
        return routeRepository.findById(id).orElseThrow(()->new RouteDoesntExistException(Constant.ROUTE_DOESNT_EXIST));
    }

    protected List<Route> findByStartingPoint_IdAndEndingPoint_Id(int startingPointId, int endingPointId){
      return  routeRepository.findByStartingPoint_IdAndEndingPoint_Id(startingPointId,endingPointId);
    }

}
