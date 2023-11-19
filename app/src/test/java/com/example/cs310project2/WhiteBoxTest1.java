package com.example.cs310project2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class WhiteBoxTest1 {
    @Test
    public void custom_user_creation() {
        User testUser = new User("Name", 20, "Email", "Native Speaker", "PW", true, true, true);
        assertEquals("Name", testUser.getName());
        assertEquals(String.valueOf(20), String.valueOf(testUser.getAge()));
        assertEquals("Email", testUser.getEmail());
        assertEquals("Native Speaker", testUser.getType());
        assertEquals("PW", testUser.getPassword());
        assertEquals(true, testUser.getLikesReading());
        assertEquals(true, testUser.getLikesMusic());
        assertEquals(true, testUser.getLikesSports());
    }

    @Test
    public void default_user_creation() {
        User testUser = new User();
        assertEquals("", testUser.getName());
        assertEquals(String.valueOf(0), String.valueOf(testUser.getAge()));
        assertEquals("", testUser.getEmail());
        assertEquals("Native", testUser.getType());
        assertEquals("", testUser.getPassword());
        assertEquals(false, testUser.getLikesReading());
        assertEquals(false, testUser.getLikesMusic());
        assertEquals(false, testUser.getLikesSports());
    }

    @Test
    public void add_friend() {
        User testUser = new User();
        testUser.AddFriend("TestFriend");
        assertEquals(true, testUser.getFriends().contains("TestFriend"));
        assertEquals(1, testUser.getFriends().size());
    }

    @Test
    public void remove_friend() {
        User testUser = new User();
        testUser.AddFriend("TestFriend");
        assertEquals(true, testUser.getFriends().contains("TestFriend"));
        assertEquals(1, testUser.getFriends().size());
        testUser.RemoveFriend("TestFriend");
        assertEquals(false, testUser.getFriends().contains("TestFriend"));
        assertEquals(0, testUser.getFriends().size());
    }

    public void read_from_db() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        String userName = "cksubram";
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference reference = root.getReference("users/" + userName);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currUser = snapshot.getValue(User.class);
                assertEquals("Carlos Subramanian", currUser.getName());
                assertEquals(String.valueOf(22), String.valueOf(currUser.getAge()));
                assertEquals("cksubram", currUser.getEmail());
                assertEquals("Native Speaker", currUser.getType());
                assertEquals("PW", currUser.getPassword());
                assertEquals(true, currUser.getLikesReading());
                assertEquals(true, currUser.getLikesMusic());
                assertEquals(true, currUser.getLikesSports());
                latch.countDown(); // Decrement the latch count
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                fail("Firebase error: " + error.getMessage());
                latch.countDown();
            }
        });

        latch.await(); // Wait for the latch to reach zero
    }
}
