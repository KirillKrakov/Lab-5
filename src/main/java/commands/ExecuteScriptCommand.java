package commands;

import managers.ConsoleManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс, представляющий команду execute_script, считывающий и исполняющий скрипт из указанного файла
 */
public class ExecuteScriptCommand extends AbstractCommand {

    public ExecuteScriptCommand() {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
    }

    /**
     * Метод проверяет наличие скрипта по указанному адресу. Само чтение и выполнение скрипта реализовано в ConsoleManager
     * @param argument - путь до файла со скриптом
     */
    @Override
    public void execute(String argument) {
        try (BufferedReader br = new BufferedReader(new FileReader(argument))){
            System.out.println("Скрипт из файла " + argument + " загружен!");
            System.out.println("$");
        } catch (IOException ex) {
            ConsoleManager.printError("Операция ввода была прервана!");
        }
    }
}
