package com.ahmetdayi.ticketapp.entity.request;

import com.ahmetdayi.ticketapp.core.validator.ValidPassword;
import com.ahmetdayi.ticketapp.core.validator.update.UpdatePasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UpdatePasswordMatches(message ="Password dont match")
public class UpdateClientRequest {

    @NotNull
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String gender;

    @ValidPassword
    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;
}
