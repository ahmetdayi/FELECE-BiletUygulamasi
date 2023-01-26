package com.ahmetdayi.ticketapp.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {
    @NotNull
    private int seatCount;
}
