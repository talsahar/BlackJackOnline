package command;

import java.util.List;

public interface Command {
void execute();
void setParams(List<String> list);

}
