package org.example.dispatcher;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, хранящий доступные команды для диспетчера команд.
 */
public class AvailableCommands {
    /**
     * Множество команд без аргументов.
     */
    public static final Set<String> COMMANDS_WITHOUT_ARGS = new HashSet<>();
    /**
     * Множество команд с аргументом ID.
     */
    public static final Set<String> COMMANDS_WITH_ID_ARG = new HashSet<>();
    /**
     * Множество команд с аргументом пути.
     */
    public static final Set<String> COMMANDS_WITH_ROUTE_ARG = new HashSet<>();
    /**
     * Множество команд с аргументами ID и пути.
     */
    public static final Set<String> COMMANDS_WITH_ROUTE_ID_ARGS = new HashSet<>();
    /**
     * Множество команд, принимающих аргументом скрипт.
     */
    public static final Set<String> SCRIPT_ARGUMENT_COMMAND = new HashSet<>();

    static {
        Collections.addAll(COMMANDS_WITHOUT_ARGS,
                "help",
                "show",
                "info",
                "exit",
                "clear",
                "shuffle"
        );
        Collections.addAll(COMMANDS_WITH_ID_ARG,
                "remove_by_id"
        );
        Collections.addAll(COMMANDS_WITH_ROUTE_ARG,
                "add",
                "filter_by_distance",
                "group_counting_by_distance",
                "remove_lower",
                "remove_greater",
                "remove_any_by_distance"
        );
        Collections.addAll(COMMANDS_WITH_ROUTE_ID_ARGS,
                "update");
        SCRIPT_ARGUMENT_COMMAND.add("execute_script");
    }
}
