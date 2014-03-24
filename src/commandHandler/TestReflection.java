package commandHandler;

import org.reflections.Reflections;

public class TestReflection {
	public static void main(String args[]){
		Reflections reflections = new Reflections();//"commandHandler.parser.impl");
//		Reflections reflections = new Reflections(ClasspathHelper.forPackage("my.package.prefix"));

//		Set<Class<? extends CommandLogic>> allClasses = reflections.getSubTypesOf(CommandLogic.class);
		
//		for(Class<? extends CommandLogic> c:allClasses){System.out.println(c);}
	}
}
