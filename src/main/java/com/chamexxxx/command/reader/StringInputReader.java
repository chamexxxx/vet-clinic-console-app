package com.chamexxxx.command.reader;

import com.chamexxxx.Message;

import java.util.Scanner;

public class StringInputReader implements InputReader<String> {
    @Override
    public String read(String prompt) {
        System.out.print(
            Message.info(prompt + ": ")
        );

        var scanner = new Scanner(System.in);

        var line = "";

        while (line.isBlank()) {
            line = scanner.nextLine();
        }

        return line;
    }
}
