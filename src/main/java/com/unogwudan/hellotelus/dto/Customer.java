package com.unogwudan.hellotelus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String address;


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", email=" + email +
                ", address=" + address +
                '}';
    }
}
