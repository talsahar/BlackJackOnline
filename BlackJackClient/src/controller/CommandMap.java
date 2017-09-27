package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import command.AbstractCommand;
import command.Command;
import command.authroizeCommands.LoginSendCommand;
import command.authroizeCommands.LoginResponseCommand;
import command.authroizeCommands.RegisterSendCommand;
import command.authroizeCommands.RegisterResponseCommand;
import command.gameCommands.GameStartCommand;
import command.gameInvitationCommands.ReceiveInvationCommand;
import command.gameInvitationCommands.ReceiveRejectionCommand;
import command.gameInvitationCommands.SendInvationCommand;
import command.lobbyCommands.ChatMessageRecievedCommand;
import command.lobbyCommands.ChatMessageSendCommand;
import command.lobbyCommands.UpdateUserListCommand;
import command.onBootCommands.ViewModelBindingCommand;
import model.Model;
import src.Config;
import view.View;

public class CommandMap {
	private Map<String, Command> commandMap;
public CommandMap() {
	commandMap=new HashMap<>();
}
public void initialMap(View view,Model model) {
	Set<Class<? extends AbstractCommand>> list=new CommandClassLoader().commandSet("command");
	list.forEach(commandClass->{
		try {
			AbstractCommand command=commandClass.getDeclaredConstructor(View.class,Model.class).newInstance(view,model);
			commandMap.put(command.getKey(), command);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	});
	


}
public Command getCommand(String key) {
		return commandMap.get(key);
	}
}
