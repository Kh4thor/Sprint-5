package ru.Egor.Malyshev.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ru.Egor.Malyshev.interfaces.HasSabtask;

public class Maintask extends Task implements HasSabtask {

	private Map<Integer, Subtask> SubtaskMap = new HashMap<>();

	// конструктор для созлания главной задачи
	public Maintask(String name, String discription) {
		super(name, discription, TaskProgress.NEW);
	}

	// конструктор для обновления главной задачи
	public Maintask(int id, String name, String discription) {
		super(id, name, discription, TaskProgress.NEW);
	}

	// очистить хранилище подзадач
	@Override
	public void clearSubtaskMap() {
		if (SubtaskMap != null) {
			SubtaskMap.clear();
		}
	}

	// удалить подзадачу из хранилища
	@Override
	public void removeSubtask(int SubtaskId) {
		if (SubtaskId > 0 && SubtaskMap.containsKey(SubtaskId)) {
			SubtaskMap.remove(SubtaskId);
		}
	}

	// добавить подзадачу в хранилище
	@Override
	public void addSubtaskToDepo(Subtask Subtask) {
		SubtaskMap.put(Subtask.getId(), Subtask);
	}

	// получить хранилище подзадач
	@Override
	public Map<Integer, Subtask> getSubtaskMap() {
		return SubtaskMap;
	}

	@Override
	public String toString() {
		return "Maintask [id=" + id + ", name=" + name + ", discription=" + discription + ", taskProgress="
				+ taskProgress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(SubtaskMap);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maintask other = (Maintask) obj;
		return Objects.equals(SubtaskMap, other.SubtaskMap);
	}
}
