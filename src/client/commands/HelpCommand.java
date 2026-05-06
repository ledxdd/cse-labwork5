package client.commands;

import common.services.command_fabric.Command;
import common.services.command_fabric.CommandReg;

import java.util.Map;

public class HelpCommand implements Command {
    private final CommandReg registry;

    public HelpCommand(CommandReg reg) {
        this.registry = reg;
    }
    @Override
    public Object execute(Object arg) throws Exception {
        System.out.println("--- Список доступных команд ---");

        System.out.println("""
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
                average_of_health : вывести среднее значение поля health для всех элементов коллекции
                count_less_than_chapter chapter : вывести количество элементов, значение поля chapter которых меньше заданного
                """);

        return null;
    }
}
