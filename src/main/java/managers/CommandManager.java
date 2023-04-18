package managers;

import commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, управляющий командами
 */
public class CommandManager {
    private final int HISTORY_SIZE = 15;
    private String[] commandsHistoryList = new String[HISTORY_SIZE];
    private List<AbstractCommand> CommandList = new ArrayList<>();

    private AbstractCommand helpCommand;
    private AbstractCommand infoCommand;
    private AbstractCommand showCommand;
    private RequestCommand addCommand;
    private RequestCommand updateIdCommand;
    private AbstractCommand removeByIdCommand;
    private AbstractCommand clearCommand;
    private AbstractCommand saveCommand;
    private AbstractCommand executeScriptCommand;
    private AbstractCommand exitCommand;
    private RequestCommand addIfMinCommand;
    private RequestCommand removeLowerCommand;
    private AbstractCommand historyCommand;
    private RequestCommand removeAllByAuthorCommand;
    private AbstractCommand countLessThanMinimalPointCommand;
    private AbstractCommand filterContainsNameCommand;

    public CommandManager(RequestManager requestManager, CollectionManager collectionManager) {
        this.helpCommand = new HelpCommand(this);
        this.infoCommand = new InfoCommand(collectionManager);
        this.showCommand = new ShowCommand(collectionManager);
        this.addCommand = new AddCommand(collectionManager, requestManager);
        this.updateIdCommand = new UpdateIdCommand(collectionManager, requestManager);
        this.removeByIdCommand = new RemoveByIdCommand(collectionManager);
        this.clearCommand = new ClearCommand(collectionManager);
        this.saveCommand = new SaveCommand(collectionManager);
        this.executeScriptCommand = new ExecuteScriptCommand();
        this.exitCommand = new ExitCommand();
        this.addIfMinCommand = new AddIfMinCommand(collectionManager, requestManager);
        this.removeLowerCommand = new RemoveLowerCommand(collectionManager, requestManager);
        this.historyCommand = new HistoryCommand(this);
        this.removeAllByAuthorCommand = new RemoveAllByAuthorCommand(collectionManager, requestManager);
        this.countLessThanMinimalPointCommand = new CountLessThanMinimalPointCommand(collectionManager);
        this.filterContainsNameCommand = new FilterContainsNameCommand(collectionManager);

        CommandList.add(helpCommand);
        CommandList.add(infoCommand);
        CommandList.add(showCommand);
        CommandList.add((AbstractCommand) addCommand);
        CommandList.add((AbstractCommand) updateIdCommand);
        CommandList.add(removeByIdCommand);
        CommandList.add(clearCommand);
        CommandList.add(saveCommand);
        CommandList.add(executeScriptCommand);
        CommandList.add(exitCommand);
        CommandList.add((AbstractCommand) addIfMinCommand);
        CommandList.add((AbstractCommand) removeLowerCommand);
        CommandList.add(historyCommand);
        CommandList.add((AbstractCommand) removeAllByAuthorCommand);
        CommandList.add(countLessThanMinimalPointCommand);
        CommandList.add(filterContainsNameCommand);
    }

    /**
     * Метод, возвращающий историю вводимых команд
     * @return commandsHistoryList (список последних команд без аргументов длиной до 15)
     */
    public String[] getCommandsHistoryList() {
        return commandsHistoryList;
    }

    /**
     * Метод, возвращающий список всех команд
     * @return commandList
     */
    public List<AbstractCommand> getCommandList() {
        return CommandList;
    }

    /**
     * Метод, добавляющий в историю вводимых команд последнюю команду
     * @param inputCommand (последняя введенная команда без аргументов)
     */
    public void addToHistory(String inputCommand) {
        for (AbstractCommand abstractCommand : CommandList) {
            if (abstractCommand.getName().split(" ")[0].equals(inputCommand)) {
                for (int i = HISTORY_SIZE-1; i>0; i--) {
                    commandsHistoryList[i] = commandsHistoryList[i-1];
                }
                commandsHistoryList[0] = inputCommand;
            }
        }
    }

    /**
     * Метод, выполняющий введённую команду
     * @param inputCommand (введённая команда)
     * @param argument (аргумент этой команды)
     */
    public void doCommand(String inputCommand, String argument) {
        boolean flag = true;
        for (AbstractCommand abstractCommand : CommandList) {
            if (abstractCommand.getName().split(" ")[0].equals(inputCommand)) {
                abstractCommand.execute(argument);
                flag = false;
            }
        }
        if (flag) System.out.println("Такой команды не существует!");
    }

    /**
     * Метод, возвращающий менеджер запроса в скрипт-режиме для команд, требующих ввода данных от пользователя по строке
     * @param inputCommand
     * @return requestCommand
     */
    public RequestCommand getRequestCommand(String inputCommand){
        if (inputCommand.equals("add")) return addCommand;
        else if (inputCommand.equals("update")) return updateIdCommand;
        else if (inputCommand.equals("add_if_min")) return addIfMinCommand;
        else if (inputCommand.equals("remove_lower")) return removeLowerCommand;
        else if (inputCommand.equals("remove_all_by_author")) return removeAllByAuthorCommand;
        else return null;
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команд, требующих ввода данных от пользователя по строке
     * @param inputCommand
     * @param requestManager
     */
    public void setRequestCommand(RequestCommand inputCommand, RequestManager requestManager) {
        if (inputCommand != null) inputCommand.setRequest(requestManager);
    }
}