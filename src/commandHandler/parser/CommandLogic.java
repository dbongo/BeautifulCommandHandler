package commandHandler.parser;

import java.util.Map;

public interface CommandLogic {
	public abstract void doAction(Map<String, Object> environment);
}
