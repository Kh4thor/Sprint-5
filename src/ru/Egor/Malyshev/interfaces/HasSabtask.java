package ru.Egor.Malyshev.interfaces;

import java.util.Map;

import ru.Egor.Malyshev.model.Subtask;

public interface HasSabtask {

	// получить хранилище подзадач
	public Map<Integer, Subtask> getSubtaskMap();

	// очистить хранилище подзадач
	public void clearSubtaskMap();

	// удалить подзадачу
	public void removeSubtask(int subTaskId);

	// добавить подзадачу в хранилище
	public void addSubtaskToDepo(Subtask subTask);
}
