package commands;

import data.LabWork;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.RequestManager;

import java.time.LocalDateTime;

/**
 * Класс, представляющий команду add_if_min, добавляющую новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */
public class AddIfMinCommand extends AbstractCommand {
    private CollectionManager collection;
    private RequestManager request;

    public AddIfMinCommand(CollectionManager collection, RequestManager request) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
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
     * Метод добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongCommandArgumentException();
            LabWork addingLabWork = new LabWork(
                    collection.generateNextId(),
                    request.requestName(),
                    request.requestCoordinates(),
                    LocalDateTime.now(),
                    request.requestMinimalPoint(),
                    request.requestPersonalQualitiesMinimum(),
                    request.requestAveragePoint(),
                    request.requestDifficulty(),
                    request.requestPerson()
            );
            if (collection.collectionSize() == 0 || addingLabWork.compareTo(collection.getFirst()) < 0) {
                collection.addToCollection(addingLabWork);
                System.out.println("Элемент добавлен в коллекцию");
            } else {
                System.out.println("Значение элемента не является наименьшим среди элементов коллекции");
            }
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
