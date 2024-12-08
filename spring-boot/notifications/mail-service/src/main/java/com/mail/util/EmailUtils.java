package com.mail.util;

/*
    https://www.baeldung.com/java-email-validation-regex
*/
public class EmailUtils {

    // Regular expression by RFC 5322 for Email Validation
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    public static boolean isEmail(String email) {
        return (email != null && email.matches(REGEX_EMAIL));
    }

    public static void main(String[] args) {
        String[] emailAddress = {
                // valid
                "username@domain.com",
                "username@domain.com",
                "user.name@domain.com",
                "user-name@domain.com",
                "username@domain.co.in",
                "user_name@domain.com",
                // invalid
                "username.@domain.com",
                ".user.name@domain.com",
                "user-name@domain.com.",
                "username@.com",
                "あいうえお@example.com",
                "email@111",
                "email",
                "email@.com.my",
                "email123@gmail.",
                "email123@.com",
                "email123@.com.com",
                ".email@email.com",
                "email()*@gmAil.com",
                "eEmail()*@gmail.com",
                "email@%*.com",
                "email..2002@gmail.com",
                "email.@gmail.com",
                "email@email@gmail.com",
                "email@gmail.com."
        };

        for (String email : emailAddress) {
            System.out.println(email + " -> " + isEmail(email));
        }
    }

}
