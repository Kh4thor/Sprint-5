package ru.Egor.Malyshev.interfaces;

import java.util.List;

import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.Maintask;
import ru.Egor.Malyshev.model.Subtask;

public interface TaskManager {

	// получить историю задач (последние 10 задач)
	List<Task> getHistory() throws Exception;
	
	// создать задачу
	Integer addTask(Task task);

	// создать главную задачу
	Integer addMaintask(Maintask Maintask);

	// создать подзадачу
	Integer addSubtask(Subtask Subtask);

	// обновить задачу
	Integer updateTask(Task newTask);

	// обновить главную задачу
	Integer updateMaintask(Maintask newMaintask);

	// обновить подзадачу
	Integer updateSubtask(Subtask newSubtask);

	// получить задачу по id
	Task getTask(int taskId) throws Exception;

	// получить главную задачу по id
	Maintask getMaintask(int MaintaskId) throws Exception;

	// получить подзадачу по id-подзадачи
	Subtask getSubtask(int id) throws Exception;

	// получить список всех задач
	List<Task> getTasksList();

	// полчучить список всех главных задач
	List<Maintask> getMaintasksList();

	// получить список всех подзадач
	List<Subtask> getSubtasksList();

	// получить список подзадач по главной задаче
	List<Subtask> getSubtaskListByMaintask(int MaintaskId);

	// удалить задачу по id
	Integer deleteTaskById(int taskId);

	// удалить главную задачу по id
	Integer deleteMaintaskById(int MaintaskId);

	// удалить подзадачу по id
	Integer deleteSubtaskById(int sTaskId);

	// удалить все задачи из taskMap
	Integer deleteAllTasks() throws Exception;

	// удалить все главные задачи
	Integer deleteAllMaintasks() throws Exception;

	// удалить все подзадачи для всех главных задач
	Integer deleteAllSubtasks() throws Exception;

	// очистить все хранилища
	Integer clearAllDepos() throws Exception;
}