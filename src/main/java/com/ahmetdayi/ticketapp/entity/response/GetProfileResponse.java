package com.ahmetdayi.ticketapp.entity.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProfileResponse {

    private int id;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private List<BuyTicketResponseInUser> buyTicket;
}
