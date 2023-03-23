package commands;

import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду clear, очищяющую коллекцию
 */
public class ClearCommand extends AbstractCommand {
    private CollectionManager collection;
    public ClearCommand(CollectionManager collection) {
        super("clear", "очистить коллекцию");
        this.collection = collection;
    }

    /**
     * Метод очищает коллекцию, удаляя из неё все элементы
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            collection.clearCollecton();
            System.out.println("Коллекция очищена");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
