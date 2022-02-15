package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;


    private final String comregex = "^(.+)@(.+).com$";

    Pattern pattern=Pattern.compile(comregex);



    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email");

        }
        this.email = email;
    }
    @Override
    public String toString(){
        return "First Name: "+firstName+" Last Name: "+lastName+"Email: "+email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
