
import commands.*;
import managers.*;

import java.io.*;

import static java.lang.System.getenv;

public class Main {
    public static void main(String[] args) {
        String envVar = getenv("lab5");
        System.out.println("Добро пожаловать!");
        System.out.println("Используемый файл: " + envVar);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        RequestManager requestManager = new RequestManager(bufferedReader);
        FileManager fileManager = new FileManager(envVar);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        HelpCommand helpCommand = new HelpCommand();
        HistoryCommand historyCommand = new HistoryCommand();
        CommandManager commandManager = new CommandManager(
                helpCommand,
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, requestManager),
                new UpdateIdCommand(collectionManager, requestManager),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new AddIfMinCommand(collectionManager, requestManager),
                new RemoveLowerCommand(collectionManager, requestManager),
                historyCommand,
                new RemoveAllByAuthorCommand(collectionManager, requestManager),
                new CountLessThanMinimalPointCommand(collectionManager),
                new FilterContainsNameCommand(collectionManager)
        );
        helpCommand.setManager(commandManager);
        historyCommand.setManager(commandManager);
        collectionManager.setCollection(fileManager.fromXMLtoCollection());
        ConsoleManager consoleManager = new ConsoleManager(commandManager, bufferedReader);
        consoleManager.interactiveMode();
    }
}