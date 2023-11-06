package com.example.cs310project2;

import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.lang.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class meeting {

    private FirebaseDatabase root;

    //basic information about the meeting
    public String host;
    private String location;
    private String time;
    private String topic;
    private ArrayList<String> members;
    private String id;

    //Constructor
    meeting(/*String creator,*/ String oLocation, String oTime, String oTopic) {
        host = /*creator*/ "";
        location = oLocation;
        time = oTime;
        topic = oTopic;
        members = new ArrayList<>();
        members.add(host);

        //save the meeting on firebase and set the id
        AddtoFireBase();
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

    public void joinMeeting(String user) {
        members.add(user);
        AddtoFireBase();
    }

    public void leaveMeeting(String user) {
        members.remove(user);
        if(user.equals(host) && members.size() > 0) { //set the host to the next available user, else set to null
            host = members.get(0);
        } else if(user.equals(host)) {
            host = "";
        }
        AddtoFireBase();
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

            AddtoFireBase();
        }
    }

    private void AddtoFireBase() {
        root = FirebaseDatabase.getInstance();


        if(id == null || id.equals("")) { //create a nonce
            id = String.valueOf(System.currentTimeMillis());
        }
        Log.d("create", "Test");
        DatabaseReference meetingRef = root.getReference("meetings/" + id + "/host");
        meetingRef.setValue(host);
        meetingRef = root.getReference("meetings/" + id + "/location");
        meetingRef.setValue(location);
        meetingRef = root.getReference("meetings/" + id + "/time");
        meetingRef.setValue(time);
        meetingRef = root.getReference("meetings/" + id + "/topic");
        meetingRef.setValue(topic);
        meetingRef = root.getReference("meetings/" + id + "/members");
        meetingRef.setValue(members);
    }
}
