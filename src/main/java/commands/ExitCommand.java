package commands;

import exceptions.WrongCommandArgumentException;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду exit, завершающую программу (без сохранения в файл)
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Метод завершает программу без сохранения коллекции в файл
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            System.out.println("Завершение программы...");
            System.exit(0);
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
