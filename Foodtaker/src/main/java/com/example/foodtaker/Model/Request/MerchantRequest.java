package com.example.foodtaker.Model.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MerchantRequest {

    private  String name;

    private  String ownerName;

    private  String email;

    private  String password;


    private String pincode;


    private String location;

    private String website;

    private String phoneNumber;

    private Double avgTransaction;

    private  Integer rating;
}
