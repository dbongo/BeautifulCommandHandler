package commandHandler.parser.impl;

import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import commandHandler.Consts;
import commandHandler.parser.CommandLogic;
import commandHandler.parser.Parser;

public class MapperCreationgCommandLogic implements CommandLogic {
	
	static{
		Parser.registerParser(new Parser() {
			@Override
			protected CommandLogic parseSpecificCommand(String command) {
				if(command.trim().equals("create_mapper"))	return new MapperCreationgCommandLogic();
				else return null;
			}

			@Override
			protected String helpString() {
				return "create_mapper: Create a new <Key,Value> mapper, where Key/Value are strings";
			}
		});
	}
	
	@Override
	public void doAction(Map<String, Object> environment) {
		environment.put(Consts.MAPPING, new TreeMap<String, String>());
		PrintStream os = (PrintStream) environment.get(Consts.outputStream);
		os.println("A new mapping has been created.");
	}
}

