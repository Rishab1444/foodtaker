package com.example.foodtaker.Controller;

import com.example.foodtaker.Entity.MerchantDetail;
import com.example.foodtaker.Model.Request.MerchantRequest;
import com.example.foodtaker.Model.Request.MerchantUpdateRequest;
import com.example.foodtaker.Model.Response.MerchantResponse;
import com.example.foodtaker.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantResponse> saveDetails(@RequestBody MerchantRequest merchantRequest){

        return new ResponseEntity<>(merchantService.saveDetails(merchantRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> saveDetails(@PathVariable(value = "id") String id){

        merchantService.deleteDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public  ResponseEntity<MerchantResponse> updateDetails(@RequestBody MerchantUpdateRequest merchantUpdateRequest){
        try {
            merchantService.updateDetails(merchantUpdateRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> findAllrestaurent(){
        return new ResponseEntity<>(merchantService.allRestaurentDetail(),HttpStatus.FOUND);
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<MerchantDetail>> findRestaurentbyName(@RequestParam String name){
        return new ResponseEntity<>(merchantService.searchbyName(name),HttpStatus.FOUND);
    }
    @PostMapping("/login/{email}")
    public ResponseEntity<?>login(@RequestParam String email,String password){
        return new ResponseEntity<>(merchantService.isValidUser(email,password), HttpStatus.CREATED);
    }


}
