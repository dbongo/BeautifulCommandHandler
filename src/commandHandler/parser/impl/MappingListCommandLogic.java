package commandHandler.parser.impl;

import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import commandHandler.Consts;
import commandHandler.parser.CommandLogic;
import commandHandler.parser.Parser;

public class MappingListCommandLogic implements CommandLogic{
	static{
		Parser.registerParser(new Parser() {
			@Override
			protected CommandLogic parseSpecificCommand(String command) {
				if(command.equals("list"))	return new MappingListCommandLogic();
				else return null;
			}
			
			@Override
			protected String helpString() {
				return "list - list the existing mappings";
			}
		});
	}

	@Override
	public void doAction(Map<String, Object> environment) {
		Map<String,String> mapper = (Map<String, String>) environment.get(Consts.mapper);
		PrintStream os = (PrintStream) environment.get(Consts.outputStream);
		
		if(mapper == null){
			os.println("Mapper non-exist yet.");
			return;
		}
		
		if(mapper.isEmpty()){
			os.println("The map is empty");
			return;
		}
		
		for(Entry<String, String> entry:mapper.entrySet()){
			os.printf("\t%s -> %s\n", entry.getKey(), entry.getValue());
		}
	}
}
