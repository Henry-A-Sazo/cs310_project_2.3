package com.example.cs310project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import android.net.Uri;
import android.view.View;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class email extends AppCompatActivity {
public Button send_btn;

    User currUser;
    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("users/" + userName);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currUser = snapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    public void sendEmail() {
        root = FirebaseDatabase.getInstance();

        EditText recipientEditText = findViewById(R.id.email_input);
        String recipientEmail = recipientEditText.getText().toString();
        String password = String.valueOf(System.currentTimeMillis());

        // Compose an email message
        String subject = "Join Talk2Friends";
        String message = "Hello, \nUse this information to log in.\nYour username is: " + recipientEmail + " please enter the password: "+password + ". This is only a temporary account.";

        String username = recipientEmail;
        reference = root.getReference("users/" + username);

        User temp = new User("", 0, username, "Narive", password, false, false, false);
        reference.setValue(temp);

        // Create an intent to send the email
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recipientEmail));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(emailIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle the case where no email app is installed.
            // You can display a message to the user or take other actions.
        }
    }
//    public void sendInvite(){
//
//        //first get the string from the textedit
//        EditText emailEditText = findViewById(R.id.email_input);
//        String email = emailEditText.getText().toString();
//
//        final String username = "310talk2friends@gmail.com";
//        final String password = "CarlosChristianHenry";
//
//        final String messageBody = "Hello,\n Your Friend " + currUser.getName() + " has invited you to Talk2Friends!\n" + "Your username is the email on which you have recieved this, and your current password is " + ".\n" + "Please login and change your password";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//
//
//        new AsyncTask<Void, Void, Exception>() {
//            @Override
//            protected Exception doInBackground(Void... voids) {
//                try {
//                    Message message = new MimeMessage(session);
//                    message.setFrom(new InternetAddress(username));
//                    message.setRecipients(Message.RecipientType.TO,
//                            InternetAddress.parse(email));
//                    message.setSubject("Invitation to Talk2Friends");
//                    message.setText(messageBody);
//                    Transport.send(message);
//                    return null; // No exception, email sent successfully
//                } catch (MessagingException e) {
//                    return e; // Return the caught exception to be processed in onPostExecute
//                }
//            }
//
//            @Override
//            protected void onPostExecute(Exception e) {
//                // onPostExecute runs on the main thread, so we can update the UI
//                if (e == null) {
//                    Log.d("create", "email success");
//                    // Start the next activity only after email has been sent successfully
//                    Intent intent = new Intent(email.this, friends.class);
//                    intent.putExtra("user", currUser.getEmail());
//                    startActivity(intent);
//                } else {
//                    // Log the exception and notify the user
//                    Log.d("create", "email error", e);
//                }
//            }
//        }.execute();
//
//        Intent intent = new Intent(this, friends.class);
//        intent.putExtra("user", currUser.getEmail());
//        startActivity(intent);
//    }
}