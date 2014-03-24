package commandHandler.parser;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commandHandler.Consts;

public abstract class Parser {
	abstract protected CommandLogic parseSpecificCommand(String command);
	abstract protected String helpString();
	
	private static List<Parser> parsers = new LinkedList<>();
	public static void registerParser(Parser parser) {
		parsers.add(parser);
	}
	
	static{
		try{
			Helper.loadClasses("commandHandler.parser.impl");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static CommandLogic parse(String command){
		command = command.trim();
		
		for(Parser parser: parsers){
			CommandLogic parsedLogicObject = parser.parseSpecificCommand(command);
			if(parsedLogicObject != null) return parsedLogicObject;
		}
		return new UnknownCommandLogic(command);
	}
	
	private static class UnknownCommandLogic implements CommandLogic {
		String givenCommand;
		
		public UnknownCommandLogic(String givenCommand){
			this.givenCommand = givenCommand;
		}
		
		@Override
		public void doAction(Map<String, Object> environment) {
			PrintStream os = (PrintStream) environment.get(Consts.outputStream);

			os.print("Unknown command: ");
			os.println(givenCommand);
			
			os.println("Acceptable commands: ");
			for(Parser parser: parsers){
				os.print('\t');
				os.println(parser.helpString());
			}
		}
	}
}

