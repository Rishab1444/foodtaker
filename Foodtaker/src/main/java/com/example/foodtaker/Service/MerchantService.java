package com.example.foodtaker.Service;

import com.example.foodtaker.Entity.MerchantDetail;
import com.example.foodtaker.Model.Request.MerchantRequest;
import com.example.foodtaker.Model.Request.MerchantUpdateRequest;
import com.example.foodtaker.Model.Response.MerchantResponse;
import com.example.foodtaker.Repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * Saving detail of restaurent
     * @param merchantRequest Contains request body
     * @return
     */
    public MerchantResponse saveDetails(MerchantRequest merchantRequest){
        MerchantDetail merchantDetail  = new MerchantDetail();
        merchantDetail.setName(merchantRequest.getName());
        merchantDetail.setOwnerName(merchantRequest.getOwnerName());
        merchantDetail.setEmail(merchantRequest.getEmail());
        merchantDetail.setPassword(merchantRequest.getPassword());
        merchantDetail.setPincode(merchantRequest.getPincode());
        merchantDetail.setPhoneNumber(merchantRequest.getPhoneNumber());
        merchantDetail.setWebsite(merchantRequest.getWebsite());
        merchantDetail.setAvgTransaction(merchantRequest.getAvgTransaction());
        merchantDetail.setLocation(merchantRequest.getLocation());
        merchantDetail.setRating(getRating(merchantRequest.getAvgTransaction()));

        merchantRepository.save(merchantDetail);
        MerchantResponse merchantResponse  = new MerchantResponse();
        merchantResponse.setStatus("Registration Successfully Created");
        return  merchantResponse;
    }

    /**
     * Deleting  the detail of the restaurent
     * @param id
     */
    public MerchantResponse deleteDetails(String id){
        MerchantResponse merchantResponse = new MerchantResponse();
        if(merchantRepository.findById(Integer.parseInt(id))!=null){
            merchantRepository.deleteById(Integer.parseInt(id));
            merchantResponse.setStatus("Restaurent information deleted Succesfully");
        } else{
            merchantResponse.setStatus("Restaurent information not found");
        }
        return  merchantResponse;
    }

    /**
     * Updating Restaurent details
     * @param merchantUpdateRequest
     */
    public MerchantResponse updateDetails(MerchantUpdateRequest merchantUpdateRequest){

        MerchantDetail restaurentInfoUpdate = merchantRepository.findById(Integer.parseInt(merchantUpdateRequest.getId())).get();
        if(restaurentInfoUpdate.getId() == Integer.parseInt(merchantUpdateRequest.getId())) {
            restaurentInfoUpdate.setName(merchantUpdateRequest.getName() == null
                    || merchantUpdateRequest.getName().equals("") ? restaurentInfoUpdate.getName() : merchantUpdateRequest.getName());

            restaurentInfoUpdate.setOwnerName(merchantUpdateRequest.getOwnerName() == null
                    || merchantUpdateRequest.getOwnerName().equals("") ? restaurentInfoUpdate.getOwnerName() : merchantUpdateRequest.getOwnerName());

            restaurentInfoUpdate.setPhoneNumber(merchantUpdateRequest.getPhoneNumber() == null
                    || merchantUpdateRequest.getPhoneNumber().equals("") ? restaurentInfoUpdate.getPhoneNumber() : merchantUpdateRequest.getPhoneNumber());

            restaurentInfoUpdate.setPincode(merchantUpdateRequest.getPincode() == null
                    || merchantUpdateRequest.getPincode().equals("") ? restaurentInfoUpdate.getPincode() : merchantUpdateRequest.getPincode());

            restaurentInfoUpdate.setWebsite(merchantUpdateRequest.getOwnerName() == null ? restaurentInfoUpdate.getOwnerName() : merchantUpdateRequest.getOwnerName());

            restaurentInfoUpdate.setLocation(merchantUpdateRequest.getLocation() == null
                    || merchantUpdateRequest.getLocation().equals("") ? restaurentInfoUpdate.getLocation() : merchantUpdateRequest.getLocation());

            restaurentInfoUpdate.setEmail(merchantUpdateRequest.getEmail() == null
                    || merchantUpdateRequest.getEmail().equals("") ? restaurentInfoUpdate.getEmail() : merchantUpdateRequest.getEmail());


            restaurentInfoUpdate.setPassword(merchantUpdateRequest.getPassword() == null
                    || merchantUpdateRequest.getPassword().equals("") ? restaurentInfoUpdate.getPassword() : merchantUpdateRequest.getPassword());

            if(merchantUpdateRequest.getAvgTransaction() != null && restaurentInfoUpdate.getAvgTransaction() != merchantUpdateRequest.getAvgTransaction()){
                restaurentInfoUpdate.setRating(getRating(merchantUpdateRequest.getAvgTransaction()));
            }
            restaurentInfoUpdate.setAvgTransaction((merchantUpdateRequest.getAvgTransaction() ==null ? restaurentInfoUpdate.getAvgTransaction() : merchantUpdateRequest.getAvgTransaction()));



            merchantRepository.save(restaurentInfoUpdate);
            MerchantResponse merchantResponse = new MerchantResponse();
            merchantResponse.setStatus("Restaurent info updated Succesfully");
            return  merchantResponse;
        }
        else {
            MerchantResponse merchantResponse = new MerchantResponse();
            merchantResponse.setStatus("Restaurent not found");
           return merchantResponse;
        }
    }
    public List<MerchantDetail> allRestaurentDetail(){
        List allRestaurentInfo = merchantRepository.findAll();
        return allRestaurentInfo;
    }
    private Integer getRating(double avgTransaction){
        Integer rating;
        if(avgTransaction < 10){
            rating  =1;
        } else if (avgTransaction> 10 && avgTransaction < 20) {
             rating = 2;
        } else if (avgTransaction > 20 && avgTransaction <30) {
            rating = 3;
        } else if (avgTransaction > 30 && avgTransaction < 40) {
            rating = 4;
        } else  {
            rating =5;
        }
        return  rating;
    }
    public List<MerchantDetail> searchbyName(String name){
        List<MerchantDetail> nameofRestaurentsearched = merchantRepository.findByName(name);
        return nameofRestaurentsearched;
    }

    public  List<MerchantDetail> isValidUser(String email,String password){
        MerchantDetail loginDetail = merchantRepository.findByEmail(email).get(0);
        if(email.equals("admin@foodtaker.com") && loginDetail.getPassword().equals(password)) {
            return allRestaurentDetail();
        }
            Optional userDetail = merchantRepository.findById(loginDetail.getId());
            return  userDetail.stream().toList();
    }
    public void validatePayload(MerchantRequest payload, BindingResult result) {
        // Validate restaurant name
        if (payload.getName() == null || payload.getName().isEmpty()) {
            result.addError(new FieldError("payload", "restaurantName", "Restaurant name is required."));
        }

        // Validate phone number
        if (payload.getPhoneNumber() == null || payload.getPhoneNumber().isEmpty()) {
            result.addError(new FieldError("payload", "phoneNumber", "Phone number is required."));
        } else {
            // Additional phone number validation logic if needed
        }

        // Validate email
        if (payload.getEmail() == null || payload.getEmail().isEmpty()) {
            result.addError(new FieldError("payload", "email", "Email is required."));
        } else if (!isValidEmail(payload.getEmail())) {
            result.addError(new FieldError("payload", "email", "Invalid email format."));
        }

        // Validate password
        if (payload.getPassword() == null || payload.getPassword().isEmpty()) {
            result.addError(new FieldError("payload", "password", "Password is required."));
        } else {
            // Additional password validation logic if needed
        }

        // Validate location
        if (payload.getLocation() == null || payload.getLocation().isEmpty()) {
            result.addError(new FieldError("payload", "location", "Location is required."));
        }

        // Validate pin code
        if (payload.getPincode() == null || payload.getPincode().isEmpty()) {
            result.addError(new FieldError("payload", "pincode", "Pin code is required."));
        } else {
            // Additional pin code validation logic if needed
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[a-zA-Z_-]+?(?:\\.[a-zA-Z]{2,6})+$";
        return email.matches(regex);
    }

}

