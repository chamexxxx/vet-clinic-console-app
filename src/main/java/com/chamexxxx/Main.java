package com.chamexxxx;

import com.chamexxxx.command.CommandAnalyzer;
import com.chamexxxx.command.CommandListener;
import com.chamexxxx.command.reader.StringInputReader;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        var authenticated = false;

        while (!authenticated) {
            var login = new StringInputReader().read("Логин");
            var password = new StringInputReader().read("Пароль");

            authenticated = Authentication.authenticate(login, password);

            if (!authenticated) {
                System.out.println(
                    Message.error("Неверные данные для входа. Повторите попытку...")
                );
            }
        }

        new CommandAnalyzer(new CommandListener()).executeUntilExited();
    }
}