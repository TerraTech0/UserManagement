package com.example.usermanagement.Controller;

import com.example.usermanagement.Api.ApiResponse;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-management/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.addUser(user);
        return ResponseEntity.ok().body(new ApiResponse("user added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.updateUser(id, user);
        return ResponseEntity.ok().body(new ApiResponse("user updated successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new ApiResponse("user deleted successfully!"));
    }


    @GetMapping("/check/{username}/{password}")
    public ResponseEntity checkUserAuthentication(@PathVariable String username, @PathVariable String password){
        userService.checkUserAuthentication(username, password);
        return ResponseEntity.ok().body(new ApiResponse("this user exist"));
    }


    @GetMapping("/find-email/{email}")
    public ResponseEntity findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/find-role/{role}")
    public ResponseEntity findUserByRole(@PathVariable String role){
        List<User> users = userService.findUserByRole(role);
        return ResponseEntity.ok().body(users);
    }


    @GetMapping("/find-age/{age}")
    public ResponseEntity findUsersByAge(@PathVariable Integer age){
        List<User> users = userService.findUsersByAge(age);
        return ResponseEntity.ok().body(users);
    }

}
