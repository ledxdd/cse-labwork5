package common.services.command_fabric;

import java.util.HashMap;
import java.util.Map;

/**
 * Реестр команд для хранения и доступа к зарегистрированным командам
 * <p>Предоставляет функциональность для регистрации команд по имени,
 * их получения и проверки существования</p>
 */
public class CommandReg {
    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Регистрирует команду в реестре
     * @param name имя команды (ключ для доступа)
     * @param command объект команды, реализующий интерфейс Command
     */
    public void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Возвращает команду по ее имени
     * @param name имя зарегистрированной команды
     * @return объект команды или null, если команда с таким именем не найдена
     */
    public Command get(String name) {
        return commands.get(name);
    }

    /**
     * Проверяет, зарегистрирована ли команда с указанным именем
     * @param name имя команды для проверки
     * @return true если команда существует, false в противном случае
     */
    public boolean hasCommand(String name) {
        return commands.containsKey(name);
    }

    /**
     * Возвращает все зарегистрированные команды
     * @return карта всех команд, где ключ — имя команды, значение — объект команды
     */
    public Map<String, Command> getAll() {
        return commands;
    }
}