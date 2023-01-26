package com.ahmetdayi.ticketapp.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTripRequest {
    @NotNull
    private double price;
    @NotBlank
    private LocalDateTime departureTime;
    @NotNull
    private int vehicleId;
    @NotNull
    private int routeId;
}
