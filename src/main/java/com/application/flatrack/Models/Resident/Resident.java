package com.application.flatrack.Models.Resident;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "residents")
public class Resident {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private int phoneNumber;
    @Column(name = "is_owner")
    private boolean owner;
    @Column(name = "is_residing")
    private boolean residing;
    @Column(name = "flat_number")
    private String flatNumber;
}
