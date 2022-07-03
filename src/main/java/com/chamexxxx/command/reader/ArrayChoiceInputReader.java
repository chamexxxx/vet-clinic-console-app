package com.chamexxxx.command.reader;

import com.chamexxxx.Message;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ArrayChoiceInputReader implements InputReader<String> {
    private final String[] choices;

    public ArrayChoiceInputReader(String[] choices) {
        this.choices = choices;
    }

    @Override
    public String read(String prompt) {
        System.out.println(
            Message.info(prompt + ": ")
        );

        for (int index : IntStream.range(0, choices.length).toArray()) {
            var choice  = choices[index];

            System.out.println("\t" + (index + 1) + ". " + choice);
        }

        System.out.print("Введите свой выбор: ");

        var scanner = new Scanner(System.in);

        while (true) {
            var actionNumber = scanner.nextInt();

            if (actionNumber >= 1 && actionNumber <= choices.length) {
                return choices[actionNumber - 1];
            }

            System.out.println(
                Message.error("Неверный выбор. Повторите попытку...")
            );
        }
    }
}
