package ru.Egor.Malyshev.model;

import java.util.Objects;

public class Task {

	protected int id;
	protected String name;
	protected String discription;
	protected TaskProgress taskProgress;
	
	// конструктор для создания задачи
	public Task(String name, String discription, TaskProgress taskProgres) {
		this.name = name;
		this.discription = discription;
		this.taskProgress = taskProgres;
	}
	// конструктор для обновленной задачи
	public Task(int id, String name, String discription, TaskProgress taskProgress) {
		this.id = id;
		this.name = name;
		this.taskProgress = taskProgress;
		this.discription = discription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskProgress getTaskProgress() {
		return taskProgress;
	}

	public void setTaskProgress(TaskProgress taskProgress) {
		this.taskProgress = taskProgress;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", taskProgress=" + taskProgress + ", discription=" + discription
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(discription, id, name, taskProgress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(discription, other.discription) && id == other.id && Objects.equals(name, other.name)
				&& taskProgress == other.taskProgress;
	}
}