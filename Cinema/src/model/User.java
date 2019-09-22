package model;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
            return firstName + " " + lastName ;
    }

}
