package com.example.foodtaker.Controller;

import com.example.foodtaker.Entity.MerchantDetail;
import com.example.foodtaker.Model.Request.MerchantRequest;
import com.example.foodtaker.Model.Request.MerchantUpdateRequest;
import com.example.foodtaker.Model.Response.MerchantResponse;
import com.example.foodtaker.Service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "merchant")
@CrossOrigin(origins = "http://localhost:3000")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantResponse> saveDetails(@RequestBody MerchantRequest merchantRequest, BindingResult result){
        merchantService.validatePayload(merchantRequest,result);
        MerchantResponse merchantResponse = new MerchantResponse();
        if (result.hasErrors()) {
            // Handle validation errors
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            merchantResponse.setStatus(errors.toString());
            return  new ResponseEntity<>(merchantResponse,HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(merchantService.saveDetails(merchantRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MerchantResponse> deleteDetail(@PathVariable(value = "id") String id){

        return new ResponseEntity<>(merchantService.deleteDetails(id),HttpStatus.OK);
    }
    @PutMapping
    public  ResponseEntity<MerchantResponse> updateDetails(@RequestBody MerchantUpdateRequest merchantUpdateRequest){
        try {

            return new ResponseEntity<>(merchantService.updateDetails(merchantUpdateRequest),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> findAllrestaurent(){
        return new ResponseEntity<>(merchantService.allRestaurentDetail(),HttpStatus.FOUND);
    }
    @GetMapping("/name")
    public ResponseEntity<List<MerchantDetail>> findRestaurentbyName(@RequestParam("name") String name){
        return new ResponseEntity<>(merchantService.searchbyName(name),HttpStatus.FOUND);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestParam("email") String email,@RequestParam("password") String password){
        return new ResponseEntity<>(merchantService.isValidUser(email,password), HttpStatus.CREATED);
    }


}
