package ru.Egor.Malyshev.service;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import ru.Egor.Malyshev.interfaces.HistoryManager;
import ru.Egor.Malyshev.model.Task;

public class InMemoryHistoryManager implements HistoryManager {

	private List<Task> historyList = new LinkedList<>();

	// добавить задачу в историю
	@Override
	public Integer addToHistory(Task task) {
		if (task != null) {
			if (historyList.size() == 10) {
				historyList.remove(0);
			}
			historyList.add(task);
			return 1;
		}
		return -1;
	}

	// получить историю задач (последние 10 задач)
	@Override
	public List<Task> getHistory() throws Exception {
		if (historyList != null) {
			ArrayList<Task> historyListCopy = new ArrayList<>(historyList);
			return historyListCopy;
		}
		throw new Exception("HistoryListNotFoundException");
	}
}
