package cse_labwork5.src.command_fabric;

import java.io.IOException;
import java.util.Collections;

/**
 * Интерфейс от которого следует наследовать каждую новую комманду
 */
public interface Command {
    void execute(String arg) throws IOException;
}