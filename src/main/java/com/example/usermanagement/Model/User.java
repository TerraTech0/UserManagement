package com.example.usermanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't ba empty!")
    @Size(min = 3, message = "name length most be more than 3") //i make it 3 coz ali name is contain on 3 letters
    @Column(columnDefinition = "varchar(15) not null") // if doesn't work write not null after check!
    //check(name >=3)
    private String name;

    @NotEmpty(message = "username can't be empty!")
    @Size(min = 4, message = "username length must be more than 4")
    @Column(columnDefinition = "varchar(15) not null") //check(username >= 4)
    private String username;

    @NotEmpty(message = "password can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @Email
    @NotEmpty(message = "email can't be empty!")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "role can't be empty!")
    @Pattern(regexp = "^(Admin)$")
    private String role;

    @NotNull(message = "age can't be null!")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
