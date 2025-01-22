package ru.Egor.Malyshev.interfaces;

import java.util.List;

import ru.Egor.Malyshev.model.Task;

public interface HistoryManager<T extends Task> {

	// добавить задачу в историю
	Integer addToHistory(T task);

	// получить историю задач (последние 10 задач)
	List<Task> getHistory() throws Exception;

}
