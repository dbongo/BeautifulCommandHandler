package commandHandler;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import commandHandler.parser.CommandLogic;
import commandHandler.parser.Parser;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Map<String, Object> environment = new TreeMap<String, Object>();
		setup(environment);
		
		PrintStream writter = (PrintStream) environment.get(Consts.outputStream);
		writter.println("Welcome to use this parser. Type command with enter key :)");
		
		while(true){
			writter.print("$ ");writter.flush();
			String command = sc.nextLine();
			CommandLogic commandLogic = Parser.parse(command);
			commandLogic.doAction(environment);
		}
	}
	
	private static void setup(Map<String, Object> environment) {
		environment.put(Consts.outputStream, System.out);
	}
}
