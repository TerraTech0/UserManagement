package com.example.usermanagement.Service;

import com.example.usermanagement.Api.ApiException;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User u = userRepository.findUserById(id);
        if (u == null){
            throw new ApiException("id not found!");
        }
        u.setName(user.getName());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        userRepository.save(u);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (id == null){
            throw new ApiException("id not found!");
        }
        userRepository.delete(user);
    }

    public User checkUserAuthentication(String username, String pssword){
        User user = userRepository.checkUserAuthentication(username, pssword);
        if (user == null){
            throw new ApiException("user not found!");
        }
        return user;
    }

    public User findUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if (email == null){
            throw new ApiException("user not found!");
        }
        return user;
    }

    public List<User> findUserByRole(String role){
        List<User> users = userRepository.findUserByRole(role);
        if (users.isEmpty()){
            throw new ApiException("user not found!");
        }
        return users;
    }

    public List<User> findUsersByAge(Integer age){
        List<User> users = userRepository.findUserByAgeGreaterThanEqual(age);
        if (users.isEmpty()){
            throw new ApiException("user not found!");
        }
        return users;
    }


}
