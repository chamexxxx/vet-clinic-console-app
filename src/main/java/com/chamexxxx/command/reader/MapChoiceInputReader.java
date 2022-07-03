package com.chamexxxx.command.reader;

import com.chamexxxx.Message;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MapChoiceInputReader<K> implements InputReader<K> {
    private final Map<K, String> actions;

    public MapChoiceInputReader(Map<K, String> actions) {
        this.actions = actions;
    }

    @Override
    public K read(String prompt) {
        System.out.println(
            Message.info(prompt + ": ")
        );

        var keySet = actions.keySet();
        var keys = new ArrayList<>(keySet);

        for (int index : IntStream.range(0, keys.size()).toArray()) {
            var key = keys.get(index);
            var value = actions.get(key);

            System.out.println("\t" + (index + 1) + ". " + value);
        }

        System.out.print("Введите свой выбор: ");

        var scanner = new Scanner(System.in);

        while (true) {
            var actionNumber = scanner.nextInt();

            if (actionNumber >= 1 && actionNumber <= keys.size()) {
                return keys.get(actionNumber - 1);
            }

            System.out.println(
                Message.error("Неверный выбор. Повторите попытку...")
            );
        }
    }
}
