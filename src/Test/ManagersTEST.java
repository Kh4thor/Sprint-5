package Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import ru.Egor.Malyshev.utils.Managers;
import ru.Egor.Malyshev.interfaces.TaskManager;
import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.TaskProgress;

public class ManagersTEST {

	@Test
	void getDefault_newInMemoryManagerIsNotNull_succes() {
		TaskManager tm = Managers.getDefault();
		Assertions.assertTrue(!tm.equals(null));
	}

	@Test
	void getDefaultHistory_equalsGetHistory_succes() throws Exception {
		TaskManager tm = Managers.getDefault();
		Task task1 = new Task("Task-1", "Discription", TaskProgress.NEW);
		tm.addTask(task1);
		tm.getTask(task1.getId());
		Assertions.assertTrue(Managers.getDefaultHistory().contains(task1));
	}
}
