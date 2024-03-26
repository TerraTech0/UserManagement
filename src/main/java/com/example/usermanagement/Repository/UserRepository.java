package com.example.usermanagement.Repository;

import com.example.usermanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.username=?1 and u.password=?2")
    User checkUserAuthentication(String username, String password);

    User findUserByEmail(String email);

    List<User> findUserByRole(String role);

    List<User> findUserByAgeGreaterThanEqual(Integer age);
}
