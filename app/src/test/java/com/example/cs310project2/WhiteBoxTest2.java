package com.example.cs310project2;
import android.widget.EditText;
import org.junit.Test;
import static org.junit.Assert.*;
public class WhiteBoxTest2 {
    @Test
    public void testEmailEditText() {
        // Replace R.id.emailEditText with the actual ID of your EditText
        EditText emailEditText = new EditText(/* context */ null);
        emailEditText.setId(R.id.email_input);

        // Test a valid email
        String validEmail = "test@usc.edu";
        emailEditText.setText(validEmail);
        assertTrue(isValidEmail(emailEditText.getText().toString()));

        // Test an invalid email
        String invalidEmail = "invalid-email";
        emailEditText.setText(invalidEmail);
        assertFalse(isValidEmail(emailEditText.getText().toString()));

        // Test an empty email
        String emptyEmail = "";
        emailEditText.setText(emptyEmail);
        assertFalse(isValidEmail(emailEditText.getText().toString()));
    }

    private boolean isValidEmail(String email) {
        // Basic email validation using a regular expression
        // This is a simple example and may not cover all edge cases
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return email.matches(emailPattern);
    }
}
