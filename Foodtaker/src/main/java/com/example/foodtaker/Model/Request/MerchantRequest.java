package com.example.foodtaker.Model.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MerchantRequest {

    @NotEmpty(message = "Required field missing")
    @Size(min = 2)
    private  String name;

    @NotEmpty(message = "Required field missing")
    @Pattern(regexp = "[^A-Za-z]",message = "Name should not contain digit or special character")
    private  String ownerName;

    private  String email;

    private  String password;


    @NotEmpty(message = "Required field missing")
    @Size(min = 6,max = 7)
    private String pincode;

    @NotEmpty(message = "Required field missing")
    private String location;

    private String website;

    @NotEmpty(message = "Required field missing")
    @Pattern(regexp = "[^0-9]")
    private String phoneNumber;

    private Double avgTransaction;

    private  Integer rating;
}
