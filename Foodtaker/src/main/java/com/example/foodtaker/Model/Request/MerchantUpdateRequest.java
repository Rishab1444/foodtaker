package com.example.foodtaker.Model.Request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MerchantUpdateRequest {

    private String id;

    private  String name;

    private  String ownerName;

    private  String Email;

    private  String password;

    private String pincode;

    private String location;

    private String website;

    private String phoneNumber;

    private Double avgTransaction;
}