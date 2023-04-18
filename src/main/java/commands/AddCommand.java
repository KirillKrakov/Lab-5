package commands;

import data.LabWork;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.RequestManager;

import java.time.LocalDateTime;

/**
 * Класс, представляющий команду add, которая добавляет новый элемент в коллекцию
 */
public class AddCommand extends AbstractCommand implements RequestCommand{
    private CollectionManager collection;
    private RequestManager request;

    public AddCommand(CollectionManager collection, RequestManager request) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collection = collection;
        this.request = request;
    }

    /**
     * Метод возвращает используемый командой менеджер коллекций
     * @return collectionManager
     */
    public CollectionManager getCollection() {
        return collection;
    }

    /**
     * Метод устанавливает используемый командой менеджер запросов
     * @param request2
     */
    public void setRequest(RequestManager request2) {
        this.request = request2;
    }

    /**
     * Метод добавляет новый элемент в коллекцию
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            collection.addToCollection(new LabWork(
                    collection.generateNextId(),
                    request.requestName(),
                    request.requestCoordinates(),
                    LocalDateTime.now(),
                    request.requestMinimalPoint(),
                    request.requestPersonalQualitiesMinimum(),
                    request.requestAveragePoint(),
                    request.requestDifficulty(),
                    request.requestPerson()
            ));
            System.out.println("Элемент добавлен в коллекцию");
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
