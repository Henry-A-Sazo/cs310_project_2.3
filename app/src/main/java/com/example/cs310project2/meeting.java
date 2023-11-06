package com.example.cs310project2;

import java.util.ArrayList;
import java.lang.*;


public class meeting {
    //basic information about the meeting
    public String host;
    private String location;
    private String time;
    private String topic;
    private ArrayList<String> members;
    private final String id;

    //Constructor
    meeting(/*String creator,*/ String oLocation, String oTime, String oTopic) {
        host = /*creator*/ "";
        location = oLocation;
        time = oTime;
        topic = oTopic;
        members = new ArrayList<>();
        members.add(host);
        id = String.valueOf(System.currentTimeMillis());
    }

    //basic getters and functions for the meeting
    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public String getID() {
        return id;
    }

    public void joinMeeting(String user) {
        members.add(user);
    }

    public void leaveMeeting(String user) {
        members.remove(user);
        if(user.equals(host) && members.size() > 0) { //set the host to the next available user, else set to null
            host = members.get(0);
        } else if(user.equals(host)) {
            host = "";
        }
    }

    public void updateMeeting(String user, String newTime, String newLocation, String newTopic) {
        if(user.equals(host)) {
            if(!newTime.equals("")) {
                time = newTime;
            }
            if(!newLocation.equals("")) {
                location = newLocation;
            }
            if(!newTopic.equals("")) {
                topic = newTopic;
            }
        }
    }
}
