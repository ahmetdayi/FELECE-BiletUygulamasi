package com.ahmetdayi.ticketapp.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRouteRequest {

    @NotNull
    private int startingPointId;
    @NotNull
    private int endingPointId;

}
