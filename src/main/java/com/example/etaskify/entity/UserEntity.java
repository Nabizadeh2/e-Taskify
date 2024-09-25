package com.example.etaskify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3,message = "Userin adi minimum 3 simvol olmalidir !!!")
    @Size(min=20,message = "Userin adi maksimum 20 simvol olmalidir")
    private String name;
    @Size(min = 5,message = "Userin soyadi minimum 5 simvol olmalidir !!!")

    private String surname;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    @ManyToOne
    private OrganizationEntity organizationEntity;


}
