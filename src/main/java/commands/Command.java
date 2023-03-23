package commands;

/**
 * Общий интерфейс для всех команд
 */
public interface Command {
    String getName();
    String getDescription();
    void execute(String argument);
}
