package commandHandler.parser.impl;

import java.util.Map;
import java.util.TreeMap;

import commandHandler.CommandLogic;
import commandHandler.Consts;
import commandHandler.parser.Parser;

public class MapperCreationgCommandLogic implements CommandLogic {
	
	static{
		System.out.println("Going to register~");
		Parser.registerParser(new Parser() {
			@Override
			protected boolean parsible(String command) {
				if(command.trim().equals("create_mapper"));
				return false;
			}
			
			@Override
			protected CommandLogic parseSpecificCommand(String command) {
				return new MapperCreationgCommandLogic();
			}

			@Override
			protected String helpString() {
				return "create_mapper: Create a new mapper";
			}
		});
	}
	
	@Override
	public void doAction(Map<String, Object> environment) {
		environment.put(Consts.MAPPING, new TreeMap<String, String>());
	}
	
	public static int cnt = 0;
}

