package ru.Egor.Malyshev.model;

import java.util.Objects;

public class Subtask extends Task {

	private int mainTaskId;

	// конструктор для создания подзадачи
	public Subtask(String name, String discription, int mainTaskId, TaskProgress taskprogres) {
		super(name, discription, taskprogres);
		this.mainTaskId = mainTaskId;
	}

	// конструкторо для обновления подзадачи
	public Subtask(int SubtaskId, String name, String discription, int mainTaskId, TaskProgress taskProgress) {
		super(SubtaskId, name, discription, taskProgress);
		this.mainTaskId = mainTaskId;
	}

	public int getMaintaskId() {
		return mainTaskId;
	}

	@Override
	public String toString() {
		return "Subtask [mainTaskId=" + mainTaskId + ", id=" + id + ", name=" + name + ", discription=" + discription
				+ ", taskProgress=" + taskProgress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(mainTaskId);
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
		Subtask other = (Subtask) obj;
		return mainTaskId == other.mainTaskId;
	}
}
