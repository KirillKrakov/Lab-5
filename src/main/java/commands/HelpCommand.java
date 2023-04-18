package commands;

import exceptions.WrongCommandArgumentException;
import managers.CommandManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду help, выводящую справку по доступным командам
 */
public class HelpCommand extends AbstractCommand {
    private CommandManager manager;

    public HelpCommand(CommandManager manager){
        super("help", "вывести справку по доступным командам");
        this.manager = manager;
    }

    /**
     * Метод выводит справку по доступным командам
     * @param argument
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty())  throw new WrongCommandArgumentException();
            for (AbstractCommand abstractCommand : manager.getCommandList()) {
                System.out.println(abstractCommand.getName()+ " : " + abstractCommand.getDescription());
            }
        } catch (WrongCommandArgumentException ex) {
            ConsoleManager.printError("Аргумент этой команды должен быть пустым!");
        }
    }
}
