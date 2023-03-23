package commands;

/**
 * Класс абстрактной команды, содержащий общие для всех команд методы, их название и описание
 */
public abstract class AbstractCommand implements Command{
    private String name;
    private String description;
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Метод возвращает название команды
     * @return command_name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод возвращает описание команды
     * @return command_description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод испольняет команду
     * @param argument
     */
    public abstract void execute(String argument);
}
