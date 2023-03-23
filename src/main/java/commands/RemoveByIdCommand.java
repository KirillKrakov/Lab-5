package commands;

import data.LabWork;
import exceptions.CollectionIsEmptyException;
import exceptions.LabWorkIsNotFoundException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду remove_by_id, удаляющую элемент из коллекции по его id
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collection;
    public RemoveByIdCommand(CollectionManager collection) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collection = collection;
    }

    /**
     * Метод удаляет элемент из коллекции по его id
     * @param argument - заданное значение id
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            LabWork removingLabWork = collection.getSameId(id);
            if (removingLabWork == null) throw new LabWorkIsNotFoundException();
            collection.removeFromCollection(removingLabWork);
            System.out.println("Элемент удалён из коллекции");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("В аргументе этой команды должен быть указан ID!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В этой коллекции нет элементов!");
        } catch (LabWorkIsNotFoundException ex) {
            ConsoleManager.printError("Элемент с таким ID не найден!");
        } catch (NumberFormatException ex) {
            ConsoleManager.printError("В аргументе команды должно быть указано число формата Integer!");
        }
    }
}
