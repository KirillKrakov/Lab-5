package commands;

import managers.RequestManager;

public interface RequestCommand extends Command{
    void setRequest(RequestManager request2);
}
