package com.geekster.Food_Delivery_Platform_API.service;

import com.geekster.Food_Delivery_Platform_API.dto.SignInInput;
import com.geekster.Food_Delivery_Platform_API.dto.SignUpOutput;
import com.geekster.Food_Delivery_Platform_API.model.AuthenticationToken;
import com.geekster.Food_Delivery_Platform_API.model.Order;
import com.geekster.Food_Delivery_Platform_API.model.User;
import com.geekster.Food_Delivery_Platform_API.repository.IAuthTokenRepo;
import com.geekster.Food_Delivery_Platform_API.repository.IOrderRepo;
import com.geekster.Food_Delivery_Platform_API.repository.IUserRepo;
import com.geekster.Food_Delivery_Platform_API.service.emailUtility.EmailHandler;
import com.geekster.Food_Delivery_Platform_API.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthTokenRepo authTokenRepo;

    @Autowired
    IOrderRepo orderRepo;



    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;


        String newEmail = user.getUserEmail();

        if (newEmail == null) {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);


        }
        //check if this email already exist ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if (existingUser != null) {
            signUpStatusMessage = "Email Already Registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //save the patient with new encrypted password
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            //signUpStatusMessage = "Email Registered Successfully!!! ";


            return new SignUpOutput(signUpStatus, "Email Registered Successfully !!!");
        } catch (Exception e) {
            signUpStatusMessage = "Internal Error Occured During SignUp!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus, signUpStatusMessage);


        }

    }

    public String signInUser(SignInInput signInInput) {

        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if (signInEmail == null) {


            signInStatusMessage = "Invalid email";

            return signInStatusMessage;
        }
        //check if this email already exist ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if (existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;


        }
        //match passwords
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if (existingUser.getUserPassword().equals(encryptedPassword)) {
                //session should be created since password matched and user is valid
                AuthenticationToken authToken = new AuthenticationToken(existingUser);
                authTokenRepo.save(authToken);
                EmailHandler.sendEmail("hrishimahajan19@gmail.com", "email testing", authToken.getTokenValue());
                return "Token sent to your Email";

            } else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }


    }

    public String  createOrder(Order order) {
        orderRepo.save(order);
        return "Your Order is Preparing";

    }

    public Order getOrderById(Long orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        return order.isPresent() ? order.get():null;
    }

    public String  cancelOrder(String email) {
        User user = userRepo.findFirstByUserEmail(email);

        Order order = orderRepo.findFirstByUser(user);

        orderRepo.delete(order);
        return "Order Cancelled";


    }
}
