package ru.Egor.Malyshev.service;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.Subtask;
import ru.Egor.Malyshev.model.Maintask;
import ru.Egor.Malyshev.model.TaskProgress;
import ru.Egor.Malyshev.interfaces.TaskManager;
import ru.Egor.Malyshev.interfaces.HistoryManager;

public class InMemoryTaskManager implements TaskManager {

	private int id = 1;

	private HistoryManager historyManager = new InMemoryHistoryManager();

	private Map<Integer, Task> taskMap = new HashMap<>();
	private Map<Integer, Maintask> maintaskMap = new HashMap<>();
	// хранилище с подзадачами хранится в своей главной задаче

	// получить историю задач (последние 10 задач)
	@Override
	public List<Task> getHistory() throws Exception {
		return historyManager.getHistory();
	}

	// создать задачу
	@Override
	public Integer addTask(Task task) {
		if (task != null) {
			if (task.getClass() == Task.class && task != null && !taskMap.containsKey(task.getId())
					&& task.getId() == 0) {
				task.setId(id++);
				int taskId = task.getId();
				String taskName = task.getName();
				if (!taskName.isBlank() && !taskName.isEmpty()) {
					taskMap.put(taskId, task);
					return taskId;
				}
			}
		}
		return -1;
	}

	// создать главную задачу
	@Override
	public Integer addMaintask(Maintask maintask) {
		if (maintask != null && maintask.getId() == 0 && !maintaskMap.containsKey(maintask.getId())) {
			maintask.setId(id++);
			int maintaskId = maintask.getId();
			String MaintaskName = maintask.getName();
			if (!MaintaskName.isBlank() && !MaintaskName.isEmpty()) {
				maintaskMap.put(maintask.getId(), maintask);
				return maintaskId;
			}
		}
		return -1;
	}

	// создать подзадачу
	@Override
	public Integer addSubtask(Subtask subtask) {
		if (subtask != null && subtask.getId() == 0) {
			int maintaskId = subtask.getMaintaskId();
			if (maintaskMap.containsKey(maintaskId)) {
				subtask.setId(id++);
				int subtaskId = subtask.getId();
				Maintask maintask = maintaskMap.get(maintaskId);
				String subtaskName = subtask.getName();
				if (!subtaskName.isBlank() && !subtaskName.isEmpty()) {
					maintask.addSubtaskToDepo(subtask);
					checkTaskProgress(maintask);
					return subtaskId;
				}
			}
		}
		return -1;
	}

	// обновить задачу
	@Override
	public Integer updateTask(Task newTask) {
		if (newTask != null) {
			if (newTask.getClass() == Task.class && newTask.getId() > 0 && taskMap.containsKey(newTask.getId())) {
				if (!newTask.getName().isEmpty() && !newTask.getName().isBlank()) {
					if (newTask.getTaskProgress() != null)
						taskMap.put(newTask.getId(), newTask);
					return newTask.getId();
				}
			}
		}
		return -1;
	}

	// обновить главную задачу
	@Override
	public Integer updateMaintask(Maintask newMaintask) {
		if (newMaintask != null && maintaskMap.containsKey(newMaintask.getId())) {
			String newName = newMaintask.getName();
			if (!newName.isEmpty() && !newName.isBlank()) {
				int MaintaskId = newMaintask.getId();
				Maintask Maintask = maintaskMap.get(MaintaskId);
				String newDiscription = newMaintask.getDiscription();
				Maintask.setName(newName);
				Maintask.setDiscription(newDiscription);
				return newMaintask.getId();
			}
		}
		return -1;
	}

	// обновить подзадачу
	@Override
	public Integer updateSubtask(Subtask newSubtask) {
		if (newSubtask != null) {
			int MaintaskId = newSubtask.getMaintaskId();
			if (maintaskMap.containsKey(MaintaskId)) {
				Maintask Maintask = maintaskMap.get(MaintaskId);
				if (Maintask != null) {
					Map<Integer, Subtask> SubtaskMap = Maintask.getSubtaskMap();
					if (SubtaskMap != null && SubtaskMap.containsKey(newSubtask.getId())) {
						if (!newSubtask.getName().isBlank() && !newSubtask.getName().isEmpty()) {
							SubtaskMap.put(newSubtask.getId(), newSubtask);
							checkTaskProgress(Maintask);
							return newSubtask.getId();
						}
					}
				}
			}
		}
		return -1;
	}

	// получить задачу по id
	@Override
	public Task getTask(int taskId) throws Exception {
		if (taskId > 0 && taskMap.containsKey(taskId)) {
			Task task = taskMap.get(taskId);
			historyManager.addToHistory(task);
			return task;
		}
		throw new Exception("TaskNotFoundException");
	}

	// получить главную задачу по id
	@Override
	public Maintask getMaintask(int maintaskId) throws Exception {
		if (maintaskId > 0 && maintaskMap.containsKey(maintaskId)) {
			Maintask maintask = maintaskMap.get(maintaskId);
			historyManager.addToHistory(maintask);
			return maintask;
		}
		throw new Exception("MaintaskNotFoundException");
	}

	// получить подзадачу по id-подзадачи
	@Override
	public Subtask getSubtask(int id) throws Exception {
		if (id > 0) {
			for (int maintaskId : maintaskMap.keySet()) {
				Maintask maintask = maintaskMap.get(maintaskId);
				Map<Integer, Subtask> subtaskMap = maintask.getSubtaskMap();
				if (subtaskMap.containsKey(id)) {
					for (int subtaskId : subtaskMap.keySet()) {
						Subtask subtask = subtaskMap.get(subtaskId);
						if (subtaskId == id) {
							historyManager.addToHistory(subtask);
							return subtask;
						}
					}
				}
			}
		}
		throw new Exception("SubtaskNotFoundException");
	}

	// получить список всех задач
	@Override
	public List<Task> getTasksList() {
		List<Task> allTaskList = new ArrayList<>();
		if (taskMap != null) {
			for (int taskId : taskMap.keySet()) {
				Task task = taskMap.get(taskId);
				allTaskList.add(task);
			}
		}
		return allTaskList;
	}

	// полчучить список всех главных задач
	@Override
	public List<Maintask> getMaintasksList() {
		List<Maintask> allMaintasksList = new ArrayList<>();
		if (maintaskMap != null) {
			for (int MaintaskId : maintaskMap.keySet()) {
				Maintask Maintask = maintaskMap.get(MaintaskId);
				allMaintasksList.add(Maintask);
			}
		}
		return allMaintasksList;
	}

	// получить список всех подзадач
	@Override
	public List<Subtask> getSubtasksList() {
		List<Subtask> allSubtasksList = new ArrayList<>();
		for (int MaintaskId : maintaskMap.keySet()) {
			Maintask Maintask = maintaskMap.get(MaintaskId);
			Map<Integer, Subtask> SubtaskMap = Maintask.getSubtaskMap();
			if (SubtaskMap != null) {
				for (int SubtaskId : SubtaskMap.keySet()) {
					Subtask Subtask = SubtaskMap.get(SubtaskId);
					allSubtasksList.add(Subtask);
				}
			}
		}
		return allSubtasksList;
	}

	// получить список подзадач по главной задаче
	@Override
	public List<Subtask> getSubtaskListByMaintask(int MaintaskId) {
		List<Subtask> SubtasksListByMaintask = new ArrayList<Subtask>();
		Maintask Maintask = maintaskMap.get(MaintaskId);
		if (Maintask != null) {
			Map<Integer, Subtask> SubtaskMap = Maintask.getSubtaskMap();
			for (int SubtaskId : SubtaskMap.keySet()) {
				Subtask Subtask = SubtaskMap.get(SubtaskId);
				SubtasksListByMaintask.add(Subtask);
			}
		}
		return SubtasksListByMaintask;
	}

	// удалить задачу по id
	@Override
	public Integer deleteTaskById(int taskId) {
		if (taskId > 0) {
			taskMap.remove(taskId);
			return taskId;
		}
		return -1;
	}

	// удалить главную задачу по id
	@Override
	public Integer deleteMaintaskById(int maintaskId) {
		if (maintaskMap != null && maintaskId > 0 && maintaskMap.containsKey(maintaskId)) {
			maintaskMap.remove(maintaskId);
			return maintaskId;
		}
		return -1;
	}

	// удалить подзадачу по id
	@Override
	public Integer deleteSubtaskById(int sTaskId) {
		if (maintaskMap != null) {
			for (int MaintaskId : maintaskMap.keySet()) {
				Maintask Maintask = maintaskMap.get(MaintaskId);
				Map<Integer, Subtask> SubtaskMap = Maintask.getSubtaskMap();
				if (SubtaskMap != null) {
					for (int SubtaskId : SubtaskMap.keySet()) {
						if (SubtaskId == sTaskId) {
							SubtaskMap.remove(SubtaskId);
							checkTaskProgress(Maintask);
							return sTaskId;
						}
					}
				}
			}
		}
		return -1;
	}

	// удалить все задачи из taskMap
	@Override
	public Integer deleteAllTasks() throws Exception {
		if (taskMap != null) {
			taskMap.clear();
			return 1;
		}
		throw new Exception("TaskMapNotFoundException");
	}

	// удалить все главные задачи
	@Override
	public Integer deleteAllMaintasks() throws Exception {
		if (maintaskMap != null) {
			maintaskMap.clear();
			return 1;
		}
		throw new Exception("MaintaskMapNotFoundException");
	}

	// удалить все подзадачи для всех главных задач
	@Override
	public Integer deleteAllSubtasks() throws Exception {
		if (maintaskMap != null) {
			for (int MaintaskId : maintaskMap.keySet()) {
				Maintask Maintask = maintaskMap.get(MaintaskId);
				Maintask.clearSubtaskMap();
				checkTaskProgress(Maintask);
				return 1;
			}
		}
		throw new Exception("SubtaskMapNotFoundException");
	}

	// очистить все хранилища
	@Override
	public Integer clearAllDepos() throws Exception {
		taskMap.clear();
		deleteAllMaintasks();
		return 1;
	}

	// отслеживание статуса главной задачи
	private void checkTaskProgress(Maintask Maintask) {
		int SubtaskCount_NEW = 0;
		int SubtaskCount_DONE = 0;
		Map<Integer, Subtask> SubtaskMap = Maintask.getSubtaskMap();
		for (int SubtaskId : SubtaskMap.keySet()) {
			Subtask Subtask = SubtaskMap.get(SubtaskId);
			TaskProgress SubtaskProgress = Subtask.getTaskProgress();
			if (SubtaskProgress.equals(TaskProgress.DONE)) {
				Maintask.setTaskProgress(TaskProgress.IN_PROGRESS);
				SubtaskCount_DONE++;
			}
			if (SubtaskProgress.equals(TaskProgress.NEW)) {
				SubtaskCount_NEW++;
			}
			if (SubtaskProgress.equals(TaskProgress.IN_PROGRESS)) {
				Maintask.setTaskProgress(TaskProgress.IN_PROGRESS);
			}
		}
		if (SubtaskCount_NEW == SubtaskMap.size()) {
			Maintask.setTaskProgress(TaskProgress.NEW);
		}
		if (SubtaskCount_DONE == SubtaskMap.size()) {
			Maintask.setTaskProgress(TaskProgress.DONE);
		}
	}
}