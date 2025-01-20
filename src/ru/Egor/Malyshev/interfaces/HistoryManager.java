package ru.Egor.Malyshev.interfaces;

import java.util.List;

import ru.Egor.Malyshev.model.Task;

public interface HistoryManager {

	// добавить задачу в историю
	Integer addToHistory(Task task);

	// получить историю задач (последние 10 задач)
	List<Task> getHistory() throws Exception;
}
