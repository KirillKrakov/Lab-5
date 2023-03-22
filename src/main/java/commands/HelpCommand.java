package commands;

import exceptions.WrongCommandArgumentException;
import managers.CommandManager;
import managers.ConsoleManager;

/**
 * Класс, представляющий команду help, выводящую справку по доступным командам
 */
public class HelpCommand extends AbstractCommand {
    private CommandManager manager;

    public HelpCommand(){
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Метод устанавливает менеджер команд, используемый для данной команды
     * @param manager
     */
    public void setManager(CommandManager manager) {
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
