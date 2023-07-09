package com.example.foodtaker.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Entity
@Table(name = "restaurent_info")
public class MerchantDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  String name;

    private  String ownerName;

    private  String email;

    private  String password;

    private String pincode;

    private String location;

    private String website;

    private String phoneNumber;

    private Double avgTransaction;

    private Integer rating;


}
