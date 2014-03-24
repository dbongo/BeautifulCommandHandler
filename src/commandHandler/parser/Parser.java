package commandHandler.parser;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import commandHandler.CommandLogic;
import commandHandler.Consts;

public abstract class Parser {
	abstract protected CommandLogic parseSpecificCommand(String command);
	abstract protected boolean parsible(String command);
	abstract protected String helpString();
	
	private static List<Parser> parsers = new LinkedList<>();
	public static void registerParser(Parser parser) {
		System.out.println("Parser Registered~");
		parsers.add(parser);
	}
	static{	// Loading of the classes, via reflection
		Properties processors = new Properties();
	}
	
	public static CommandLogic parse(String command){
		for(Parser parser: parsers){
			if(parser.parsible(command)){
				return parser.parseSpecificCommand(command);
			}
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
			PrintStream os = (PrintStream) environment.get(Consts.OUTPUT_KEY);

			os.print("Unknown command: ");
			os.println(givenCommand);
			
			os.println("Acceptable commands: ");
			for(Parser parser: parsers){
				os.println(parser.helpString());
			}
		}
	}
}

