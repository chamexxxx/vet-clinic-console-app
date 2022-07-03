package com.chamexxxx;

public class Message {
    public static String error(String text) {
        return ConsoleColors.RED + text + ConsoleColors.RESET;
    }

    public static String success(String text) {
        return ConsoleColors.GREEN + text + ConsoleColors.RESET;
    }

    public static String info(String text) {
        return ConsoleColors.MAGENTA + text + ConsoleColors.RESET;
    }

    public static void showError(String text) {
        System.out.println(Message.error(text));
    }
}
