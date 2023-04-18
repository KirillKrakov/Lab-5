package commands;

import data.Coordinates;
import data.Difficulty;
import data.LabWork;
import data.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.LabWorkIsNotFoundException;
import exceptions.WrongCommandArgumentException;
import managers.CollectionManager;
import managers.ConsoleManager;
import managers.RequestManager;

import java.time.LocalDateTime;

/**
 * Класс, представляющий команду update id, обновляющую значение элемента коллекции, id которого равен заданному
 */
public class UpdateIdCommand extends AbstractCommand implements RequestCommand{
    private CollectionManager collection;
    private RequestManager request;

    public UpdateIdCommand(CollectionManager collection, RequestManager request) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
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
     * Метод обновляет значение элемента коллекции, id которого равен заданному
     * @param argument - заданное значение id
     */
    @Override
    public void execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongCommandArgumentException();
            if (collection.collectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            LabWork oldLabWork = collection.getSameId(id);
            if (oldLabWork == null) throw new LabWorkIsNotFoundException();

            String name = oldLabWork.getName();
            Coordinates coordinates = oldLabWork.getCoordinates();
            LocalDateTime creationDate = oldLabWork.getCreationDate();
            int minimalPoint = oldLabWork.getMinimalPoint();
            Float personalQualitiesMinimum = oldLabWork.getPersonalQualitiesMinimum();
            long averagePoint = oldLabWork.getAveragePoint();
            Difficulty difficulty = oldLabWork.getDifficulty();
            Person author = oldLabWork.getAuthor();

            collection.removeFromCollection(oldLabWork);

            if(request.requestUpdateQuestion("Хотите изменить имя?")) name = request.requestName();
            if(request.requestUpdateQuestion("Хотите изменить координаты?")) coordinates = request.requestCoordinates();
            if(request.requestUpdateQuestion("Хотите изменить время создания?")) creationDate = LocalDateTime.now();
            if(request.requestUpdateQuestion("Хотите изменить минимальный балл?")) minimalPoint = request.requestMinimalPoint();
            if(request.requestUpdateQuestion("Хотите изменить минимум личных качеств?")) personalQualitiesMinimum = request.requestPersonalQualitiesMinimum();
            if(request.requestUpdateQuestion("Хотите изменить средний балл?")) averagePoint = request.requestAveragePoint();
            if(request.requestUpdateQuestion("Хотите изменить сложность?")) difficulty = request.requestDifficulty();
            if(request.requestUpdateQuestion("Хотите изменить автора?")) author = request.requestPerson();

            collection.addToCollection(new LabWork(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    minimalPoint,
                    personalQualitiesMinimum,
                    averagePoint,
                    difficulty,
                    author
            ));
            System.out.println("Элемент коллекции успешно обновлён!");
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
