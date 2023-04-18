package commands;

import exceptions.HistoryIsEmptyException;
import exceptions.WrongCommandArgumentException;
import managers.CommandManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду history, выводящую последние 15 команд (без их аргументов)
 */
public class HistoryCommand extends AbstractCommand {
    private CommandManager manager;

    public HistoryCommand(CommandManager manager) {
        super("history", "вывести последние 15 команд (без их аргументов)");
        this.manager = manager;
    }

    /**
     * Метод выводит последние введённые пользователем команды (без их аргументов) - до 15 штук
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try{
            String[] history = manager.getCommandsHistoryList();
            if(!argument.isEmpty()) throw new WrongCommandArgumentException();
            if(history.length == 0) throw new HistoryIsEmptyException();
            System.out.println("История использованных команд: ");
            for (int i=0; i<history.length; i++) {
                if (history[i] != null) {
                    System.out.println(history[i]);
                }
            }
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        } catch (HistoryIsEmptyException ex) {
            ConsoleManager.printError("Ни одной команды ещё не было использовано");
        }
    }
}
