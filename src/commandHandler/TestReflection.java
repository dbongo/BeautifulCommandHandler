package commandHandler;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReflection {
	
	public static Class[] getClasses(String pkgName) throws ClassNotFoundException{
		ArrayList<Class> classes = new ArrayList<>();
		
		File directory = null;
		
		ClassLoader cld = Thread.currentThread().getContextClassLoader();
		if(cld == null){
			throw new ClassNotFoundException("Can't get class loader");
		}
		String path = "./" + pkgName.replace('.', '/');
		URL resource = cld.getResource(path);
		if(resource == null){
			throw new ClassNotFoundException("No resource for " + path);
		}
		
		directory = new File(resource.getFile());
		
		if(directory.exists()){
			String[] files = directory.list();
			for(String fileName:files){
				if(fileName.endsWith(".class")){
					String className = fileName.substring(0,fileName.length() - 6);
					classes.add(Class.forName(pkgName + "." + className));
				}
			}
		} else{
			throw new ClassNotFoundException(pkgName + " doeesn't appear to be a valid package");
		}
		
		Class classesA[] = new Class[classes.size()];
		classes.toArray(classesA);
		return classesA;
	}
	
	public static void main(String args[]) throws ClassNotFoundException{
		Pattern myPattern = Pattern.compile("^map (\\w+) (.*)$");
		
		Matcher m = myPattern.matcher("map s songyy");
		System.out.println(m.matches());
		System.out.println(m.group(1));
		System.out.println(m.group(2));
	}
}
