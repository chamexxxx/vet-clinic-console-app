package com.chamexxxx.command.reader;

import com.chamexxxx.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LocalDateTimeInputReader implements InputReader<LocalDateTime> {
    private final String pattern;

    public LocalDateTimeInputReader(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalDateTime read(String prompt) {
        var formatter = DateTimeFormatter.ofPattern(pattern);
        var nowDate = LocalDateTime.now();
        var defaultDate = nowDate.format(formatter);

        System.out.printf("%s (%s): ", prompt, defaultDate);

        var scanner = new Scanner(System.in);

        var line = scanner.nextLine();

        while (!line.isBlank()) {
            try {
                return LocalDateTime.parse(line, formatter);
            } catch (DateTimeParseException ignored) {
                System.out.println(
                    Message.error("Неккоректный формат даты. Попробуйте снова...")
                );

                line = scanner.nextLine();
            }
        }

        return nowDate;
    }
}
