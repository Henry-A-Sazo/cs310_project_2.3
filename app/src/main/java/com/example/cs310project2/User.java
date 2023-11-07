package com.example.cs310project2;

import java.util.ArrayList;

public class User {
    private String name;
    private Integer age;
    private String email;
    private String type;

    private Boolean likesReading;
    private Boolean likesMusic;
    private Boolean likesSports;
    private ArrayList<User> friends;

    User() {//default constructor
        name = "";
        age = 0;
        email = "";
        type = "Native";
        likesReading = false;
        likesMusic = false;
        likesSports = false;
        friends = new ArrayList<>();
    }
    User(String oName, Integer oAge, String oEmail, String oType, Boolean oLikesReading, Boolean oLikesMusic, Boolean oLikesSports) {
        name = oName;
        age = oAge;
        email = oEmail;
        type = oType;
        likesReading = oLikesReading;
        likesMusic = oLikesMusic;
        likesSports = oLikesSports;
        friends = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public Boolean getLikesReading() {
        return likesReading;
    }

    public Boolean getLikesMusic() {
        return likesMusic;
    }

    public Boolean getLikesSports() {
        return likesSports;
    }

    public ArrayList<User> getFriends() {
        return new ArrayList<>(friends); // Return a copy of the friends list to prevent external modifications
    }
}
