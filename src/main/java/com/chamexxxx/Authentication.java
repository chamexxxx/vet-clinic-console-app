package com.chamexxxx;

public class Authentication {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    public static boolean authenticate(String login, String password) {
        return login.equals(LOGIN) && password.equals(PASSWORD);
    }
}
