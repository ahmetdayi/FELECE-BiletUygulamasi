package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.Route;
import com.ahmetdayi.ticketapp.entity.response.RouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RouteConverter {

    private final CityConverter cityConverter;

    public RouteResponse convert(Route from){
        if (from==null){
            return null;
        }
        return new RouteResponse
                (
                        from.getId(),
                        cityConverter.convert(from.getStartingPoint()),
                        cityConverter.convert(from.getEndingPoint())
                );
    }

    public List<RouteResponse> convert(List<Route> fromList){
        if (fromList==null){
            return null;
        }
      return fromList.stream().map(route -> new RouteResponse
                (
                        route.getId(),
                        cityConverter.convert(route.getStartingPoint()),
                        cityConverter.convert(route.getEndingPoint())
                )).collect(Collectors.toList());
    }
}
