package commandHandler.parser.impl;

import java.io.PrintStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commandHandler.Consts;
import commandHandler.parser.CommandLogic;
import commandHandler.parser.Parser;

public class MapperPutCommandLogic implements CommandLogic{
	static{
		Parser.registerParser(new Parser() {
			private Pattern myPattern = Pattern.compile("^map (\\w+) (.*)$");
			
			@Override
			protected CommandLogic parseSpecificCommand(String command) {
				Matcher matcher = myPattern.matcher(command);
				if(!matcher.matches())	return null;
				return new MapperPutCommandLogic(matcher.group(1),matcher.group(2));
			}
			
			@Override
			protected String helpString() {
				return "map [key] [value]: map a key to value, where key is [0-9a-zA-Z_]+, and value is a string";
			}
		});
	}

	private String key;
	private String value;

	private MapperPutCommandLogic(String key, String value) {
		this.key = key; this.value = value;
	}

	@Override
	public void doAction(Map<String, Object> environment) {
		Map<String,String> mapping = (Map<String, String>) environment.get(Consts.mapper);
		PrintStream os = (PrintStream) environment.get(Consts.outputStream);
		
		if(mapping == null){
			os.println("Mapping haven't created. Please create the mapping first.");
			return;
		}
		
		mapping.put(key, value);
		os.printf("New mapping has been saved: (%s -> %s)\n",key,value);
	}
}
