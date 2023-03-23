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
    private AddCommand addCommand;
    private UpdateIdCommand updateIdCommand;
    private AbstractCommand removeByIdCommand;
    private AbstractCommand clearCommand;
    private AbstractCommand saveCommand;
    private AbstractCommand executeScriptCommand;
    private AbstractCommand exitCommand;
    private AddIfMinCommand addIfMinCommand;
    private RemoveLowerCommand removeLowerCommand;
    private AbstractCommand historyCommand;
    private RemoveAllByAuthorCommand removeAllByAuthorCommand;
    private AbstractCommand countLessThanMinimalPointCommand;
    private AbstractCommand filterContainsNameCommand;

    public CommandManager(AbstractCommand helpCommand, AbstractCommand infoCommand, AbstractCommand showCommand,
                          AddCommand addCommand, UpdateIdCommand updateIdCommand, AbstractCommand removeByIdCommand,
                          AbstractCommand clearCommand, AbstractCommand saveCommand, AbstractCommand executeScriptCommand,
                          AbstractCommand exitCommand, AddIfMinCommand addIfMinCommand, RemoveLowerCommand removeLowerCommand,
                          AbstractCommand historyCommand, RemoveAllByAuthorCommand removeAllByAuthorCommand,
                          AbstractCommand countLessThanMinimalPointCommand, AbstractCommand filterContainsNameCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateIdCommand = updateIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.historyCommand = historyCommand;
        this.removeAllByAuthorCommand = removeAllByAuthorCommand;
        this.countLessThanMinimalPointCommand = countLessThanMinimalPointCommand;
        this.filterContainsNameCommand = filterContainsNameCommand;

        CommandList.add(helpCommand);
        CommandList.add(infoCommand);
        CommandList.add(showCommand);
        CommandList.add(addCommand);
        CommandList.add(updateIdCommand);
        CommandList.add(removeByIdCommand);
        CommandList.add(clearCommand);
        CommandList.add(saveCommand);
        CommandList.add(executeScriptCommand);
        CommandList.add(exitCommand);
        CommandList.add(addIfMinCommand);
        CommandList.add(removeLowerCommand);
        CommandList.add(historyCommand);
        CommandList.add(removeAllByAuthorCommand);
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
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды add
     * @param requestManager
     */
    public void setAddCommand(RequestManager requestManager) {
        addCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды update id
     * @param requestManager
     */
    public void setUpdateIdCommand(RequestManager requestManager) {
        updateIdCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды add_if_min
     * @param requestManager
     */
    public void setAddIfMinCommand(RequestManager requestManager) {
        addIfMinCommand.setRequest(requestManager);
    }

    /**
     * Метод, устанавливающий менеджер запроса в скрипт-режиме для команды remove_lower
     * @param requestManager
     */
    public void setRemoveLowerCommand(RequestManager requestManager) {
        removeLowerCommand.setRequest(requestManager);
    }
    public void setRemoveAllByAuthorCommand(RequestManager requestManager) {
        removeAllByAuthorCommand.setRequest(requestManager);
    }
}