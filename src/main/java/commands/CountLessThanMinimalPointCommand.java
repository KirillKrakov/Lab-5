package commands;

import data.LabWork;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду count_less_than_minimal_point, выводящую количество элементов, значение поля minimalPoint которых меньше заданного
 */
public class CountLessThanMinimalPointCommand extends AbstractCommand {
    private CollectionManager collection;

    public CountLessThanMinimalPointCommand(CollectionManager collection) {
        super("count_less_than_minimal_point minimalPoint", "вывести количество элементов, значение поля minimalPoint которых меньше заданного");
        this.collection = collection;
    }

    /**
     * Метод выводит количество элементов, значение поля minimalPoint которых меньше заданного
     * @param argument - значение поля minimalPoint
     */
    @Override
    public void execute(String argument) {
        try {
            if(argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int checkingMinimalPoint = Integer.parseInt(argument);
            int count = 0;
            for (LabWork labWork : collection.getCollection()) {
                if (labWork.getMinimalPoint() < checkingMinimalPoint) count += 1;
            }
            System.out.println("Количество элементов: " + count);
        } catch (WrongCommandArgumentException exc) {
            ConsoleManager.printError("В аргументе данной команды должен быть указан минимальный балл!");
        } catch (CollectionIsEmptyException exc) {
            ConsoleManager.printError("В коллекции нет элементов!");
        } catch (NumberFormatException exc) {
            ConsoleManager.printError("В аргументе команды должно быть указано число формата int!");
        }
    }
}
