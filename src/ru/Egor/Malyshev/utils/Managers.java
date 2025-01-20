package ru.Egor.Malyshev.utils;

import java.util.List;

import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.interfaces.TaskManager;
import ru.Egor.Malyshev.service.InMemoryTaskManager;

public final class Managers {

	static TaskManager inMemoryTaskManager = new InMemoryTaskManager();

	public static TaskManager getDefault() {
		return inMemoryTaskManager;
	}
	
	public static List<Task> getDefaultHistory() throws Exception {
		return	inMemoryTaskManager.getHistory();
	}
}
