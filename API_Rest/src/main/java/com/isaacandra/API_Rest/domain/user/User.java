package com.isaacandra.API_Rest.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    public User(CreateUserDTO data) {
        this.userName = data.userName();
        this.address = data.address();
        this.gender = data.gender();
    }

    public void updatedUser(EditUserDTO data) {
        if (data.userName() != null){
            this.userName = data.userName();
        }
        if (data.address() != null){
            this.address = data.address();
        }
        if (data.gender() != null){
            this.gender = data.gender();
        }
    }
}
