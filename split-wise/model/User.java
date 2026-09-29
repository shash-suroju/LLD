package model;

import java.util.*;

public class User {

    private final String userId;

    private final String name;


    public User (String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(!(o instanceof User)) return false;

        return userId.equals(((User) o).getUserId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(userId);
    } 


    @Override
    public String toString() {
        return name;
    }
}