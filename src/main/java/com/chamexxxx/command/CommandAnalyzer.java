package com.chamexxxx.command;

import com.chamexxxx.command.reader.ArrayChoiceInputReader;
import com.chamexxxx.command.reader.MapChoiceInputReader;
import com.chamexxxx.command.reader.LocalDateTimeInputReader;
import com.chamexxxx.command.reader.StringInputReader;
import com.chamexxxx.annotation.Command;
import com.chamexxxx.annotation.CommandContainer;
import com.chamexxxx.annotation.Option;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CommandAnalyzer {
    private final Object commandListener;
    private boolean exitActivated = false;

    public CommandAnalyzer(Object commandListener) {
        this.commandListener = commandListener;
    }

    public void executeUntilExited() throws InvocationTargetException, IllegalAccessException {
        while (!exitActivated) {
            execute();
        }
    }

    public void execute() throws InvocationTargetException, IllegalAccessException {
        var actionAliases = new HashMap<Integer, String>();
        var methods = new HashMap<Integer, Method>();

        for (Method method : commandListener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                var command = method.getAnnotation(Command.class);

                actionAliases.put(command.order(), command.name());
                methods.put(command.order(), method);
            }
        }
        
        var commandContainer = commandListener.getClass().getAnnotation(CommandContainer.class);

        var maxOrder = Collections.max(actionAliases.keySet());
        var exitCommandOrder = maxOrder + 1;

        actionAliases.put(exitCommandOrder, commandContainer.exitPrompt());

        var commandOrder = new MapChoiceInputReader<>(actionAliases).read(commandContainer.prompt());

        if (commandOrder.equals(exitCommandOrder)) {
            exitActivated = true;
            return;
        }

        var method = methods.get(commandOrder);

        var methodArgs = new ArrayList<>();

        for (Parameter parameter : method.getParameters()) {
            if (parameter.isAnnotationPresent(Option.class)) {
                var option = parameter.getAnnotation(Option.class);
                var type = parameter.getType();

                Object value;

                if (option.choices().length > 0) {
                    value = new ArrayChoiceInputReader(option.choices()).read(option.prompt());
                } else if (type.equals(LocalDateTime.class)) {
                    value = new LocalDateTimeInputReader(option.pattern()).read(option.prompt());
                } else {
                    value = new StringInputReader().read(option.prompt());
                }

                methodArgs.add(value);
            } else {
                methodArgs.add(null);
            }
        }

        var methodResult = method.invoke(commandListener, methodArgs.toArray());

        if (methodResult != null) {
            System.out.println(methodResult);
        }
    }
}
