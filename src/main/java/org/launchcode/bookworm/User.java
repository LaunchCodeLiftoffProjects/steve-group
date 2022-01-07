package org.launchcode.bookworm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;


@Entity
@Validated
public class User {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue
    private int id;


    @NotBlank
    private String firstName;

    private String lastName;
    private Date dob;
    private String userName;
    private String email;
    private String pwHash;
    private ArrayList <Book> library;

    @OneToOne
    @JoinColumn (name = "addressId")
    private Address address;

public User (){}
    public User(String userName, String password){
        this.userName = userName;
        this.pwHash = encoder.encode(password);
    }
    public void setId(int id) {
        this.id = id;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public ArrayList<Book> getLibrary() {
        return library;
    }

    public void setLibrary(ArrayList<Book> library) {
        this.library = library;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password,pwHash);
    }
}
