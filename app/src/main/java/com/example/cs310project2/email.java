package com.example.cs310project2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import java.util.Random;



public class email extends AppCompatActivity {
    private EditText mEditTextTo;
    public Button send_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        mEditTextTo = findViewById(R.id.email_input);

        send_btn = findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        int code = generateRandom4DigitNumber();
        String subject = "Invitation to Talk2Friends";
        String message = "Hello Friend,\nPlease enter this code "+ code+ " as your password and your current email to join our app!"+
                "\n\n Best, \n Talk2Friends Team";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public static int generateRandom4DigitNumber() {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate a random integer between 1000 and 9999
        int random4DigitNumber = random.nextInt(9000) + 1000;

        return random4DigitNumber;
    }
}