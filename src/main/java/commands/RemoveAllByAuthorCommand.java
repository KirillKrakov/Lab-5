package commands;

import data.LabWork;
import data.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.RequestManager;

import java.util.TreeSet;

/**
 * Класс, представляет команду remove_all_by_author, которая удаляет из коллекции все элементы, значение поля author которого эквивалентно заданному
 */
public class RemoveAllByAuthorCommand extends AbstractCommand {
    private CollectionManager collection;
    private RequestManager request;
    public RemoveAllByAuthorCommand(CollectionManager collection, RequestManager request) {
        super("remove_all_by_author author", "удалить из коллекции все элементы, значение поля author которого эквивалентно заданному");
        this.collection = collection;
        this.request = request;
    }
    /**
     * Метод устанавливает используемый командой менеджер запросов
     * @param request2
     */
    public void setRequest(RequestManager request2) {
        this.request = request2;
    }

    /**
     * Метод удаляет из коллекции все элементы, значение поля author которого эквивалентно заданному
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            if(collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            Person checkingAuthor = new Person(
                    request.requestPersonName(),
                    request.requestPersonBirthday(),
                    request.requestPersonPassportID()
            );
            TreeSet<LabWork> removingLabWorks = new TreeSet<>();
            for (LabWork labWork : collection.getCollection()) {
                if (labWork.getAuthor().equals(checkingAuthor)) {
                    System.out.println("Элемент коллекции № " + labWork.getId() + " удален");
                    removingLabWorks.add(labWork);
                }
            }
            for (LabWork removingLabWork : removingLabWorks) {
                collection.removeFromCollection(removingLabWork);
            }
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        } catch (CollectionIsEmptyException ex) {
            ConsoleManager.printError("В коллекции нет элементов!");
        }
    }
}
