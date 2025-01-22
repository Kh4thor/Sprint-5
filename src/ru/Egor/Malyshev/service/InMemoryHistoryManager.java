package ru.Egor.Malyshev.service;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import ru.Egor.Malyshev.interfaces.HistoryManager;
import ru.Egor.Malyshev.model.Maintask;
import ru.Egor.Malyshev.model.Subtask;
import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.TaskProgress;

public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T> {

	private List<Task> historyList = new LinkedList<>();

	// добавить задачу в историю
	@Override
	public Integer addToHistory(T task) {
		if (task != null) {
			if (historyList.size() == 10) {
				historyList.remove(0);
			}
			int id = task.getId();
			String name = task.getName();
			String discription = task.getDiscription();
			TaskProgress progress = task.getTaskProgress();
			if (task instanceof Task) {
				Task newTask = new Task(id, name, discription, progress);
				historyList.add(newTask);
				return 1;
			} else if (task instanceof Maintask) {
				Maintask newMaintask = new Maintask(id, name, discription);
				historyList.add(newMaintask);
				return 1;
			} else if (task instanceof Subtask) {
				int maintaskId = ((Subtask) task).getMaintaskId();
				Subtask newSubtask = new Subtask(id, name, discription, maintaskId, progress);
				historyList.add(newSubtask);
			}
		}
		return -1;
	}

	// получить историю задач (последние 10 задач)
	@Override
	public List<Task> getHistory() throws Exception {
		if (historyList != null) {
			return new ArrayList<>(historyList);
		}
		throw new Exception("HistoryListNotFoundException");
	}
}
