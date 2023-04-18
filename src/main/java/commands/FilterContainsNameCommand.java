package commands;

import data.LabWork;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду filter_contains_name, выводящую элементы, значение поля name которых содержит заданную подстроку
 */
public class FilterContainsNameCommand extends AbstractCommand {
    private CollectionManager collection;

    public FilterContainsNameCommand(CollectionManager collection) {
        super("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collection = collection;
    }

    /**
     * Метод выводит элементы, значение поля name которых содержит заданную подстроку
     * @param argument - заданная подстрока
     */
    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int k = 0;
            System.out.println("Элементы, содержащие данную подстроку: ");
            for (LabWork labWork : collection.getCollection()) {
                if (labWork.getName().contains(argument)) {
                    System.out.println(labWork);
                    k += 1;
                }
            }
            if (k == 0) System.out.println("В коллекции нет подходящих элементов!");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе данной команды должно содержаться название элемента!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В коллекции не содержится элементов!");
        }
    }
}
