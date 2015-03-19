package com.ase.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by saiteja on 2/22/2015.
 */
@XmlRootElement(namespace = "ase")
public class RegisterBean {
    private String firstName, lastName, email, password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
