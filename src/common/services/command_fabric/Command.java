package common.services.command_fabric;

import java.io.Serializable;

/**
 * Интерфейс от которого следует наследовать каждую новую комманду
 */
public interface Command {
    Object execute(Object arg) throws Exception;
}