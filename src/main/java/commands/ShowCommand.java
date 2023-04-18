package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;

/**
 * Класс, предлставляющий команду show, выводящую в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collection;
    public ShowCommand(CollectionManager collection) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collection = collection;
    }

    /**
     * Метод выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            System.out.println(collection);
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
