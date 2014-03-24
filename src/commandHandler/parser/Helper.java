package commandHandler.parser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Helper {
	public static void loadClasses(String pkgName) throws ClassNotFoundException{
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
	}

}
