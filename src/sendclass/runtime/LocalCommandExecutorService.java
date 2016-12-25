package sendclass.runtime;

public interface LocalCommandExecutorService {
	   ExecuteResult executeCommand(String command, long timeout);
	}