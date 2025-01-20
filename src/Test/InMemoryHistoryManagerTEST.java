package Test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.Egor.Malyshev.model.Maintask;
import ru.Egor.Malyshev.model.Subtask;
import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.TaskProgress;
import ru.Egor.Malyshev.service.InMemoryHistoryManager;
import ru.Egor.Malyshev.service.InMemoryTaskManager;

public class InMemoryHistoryManagerTEST {
	
	
	InMemoryTaskManager tm = new InMemoryTaskManager();
	InMemoryHistoryManager hm = new InMemoryHistoryManager();
	
	// добавить задачу в историю
		@Test
		void addToHistory_tasksNotNull_succes() {
			Task task1 = new Task("Task-1", "Discription", TaskProgress.NEW);
			Assertions.assertTrue(hm.addToHistory(task1) == 1);
		}

		@Test
		void addToHistory_tasksIsNull_failure() {
			Task task1 = null;
			Assertions.assertTrue(hm.addToHistory(task1) == -1);
		}

		// получить историю задач (последние 10 задач)
		@Test
		void getHistory_elevenTasksAdded_succes() throws Exception {
			Task task1 = new Task("Task-1", "Discription", TaskProgress.NEW);
			Task task2 = new Task("Task-2", "Discription", TaskProgress.NEW);
			Task task3 = new Task("Task-3", "Discription", TaskProgress.NEW);
			Task task4 = new Task("Task-4", "Discription", TaskProgress.NEW);
			Task task5 = new Task("Task-5", "Discription", TaskProgress.NEW);
			tm.addTask(task1);
			tm.addTask(task2);
			tm.addTask(task3);
			tm.addTask(task4);
			tm.addTask(task5);

			Maintask maintask1 = new Maintask("Maintask-1", "Discription");
			Maintask maintask2 = new Maintask("Maintask-2", "Discription");
			Maintask maintask3 = new Maintask("Maintask-3", "Discription");
			int maintaskId1 = tm.addMaintask(maintask1);
			int maintaskId2 = tm.addMaintask(maintask2);
			int maintaskId3 = tm.addMaintask(maintask3);

			Subtask subtask1 = new Subtask("Subtask-1", "Discription", maintaskId1, TaskProgress.NEW);
			Subtask subtask2 = new Subtask("Subtask-2", "Discription", maintaskId2, TaskProgress.NEW);
			Subtask subtask3 = new Subtask("Subtask-3", "Discription", maintaskId3, TaskProgress.NEW);
			tm.addSubtask(subtask1);
			tm.addSubtask(subtask2);
			tm.addSubtask(subtask3);

			hm.addToHistory(task1);
			hm.addToHistory(task2);
			hm.addToHistory(task3);
			hm.addToHistory(task4);
			hm.addToHistory(maintask1);
			hm.addToHistory(maintask2);
			hm.addToHistory(maintask3);
			hm.addToHistory(subtask1);
			hm.addToHistory(subtask2);
			hm.addToHistory(subtask3);
			hm.addToHistory(task5);

			List<Task> arr = hm.getHistory();

			Assertions.assertTrue(arr.size() == 10 &&
					!arr.contains(task1) &&
					arr.contains(task2) &&
					arr.contains(task3) &&
					arr.contains(task4) &&
					arr.contains(maintask1) &&
					arr.contains(maintask2) &&
					arr.contains(maintask3) &&
					arr.contains(subtask1) &&
					arr.contains(subtask2) &&
					arr.contains(subtask3) &&
					arr.contains(task5));
		}
		@Test
		void getHistory_sixTasksAdded_succes() throws Exception {
			Task task1 = new Task("Task-1", "Discription", TaskProgress.NEW);
			Task task2 = new Task("Task-2", "Discription", TaskProgress.NEW);
			tm.addTask(task1);
			tm.addTask(task2);
			
			Maintask maintask1 = new Maintask("Maintask-1", "Discription");
			Maintask maintask2 = new Maintask("Maintask-2", "Discription");
			int maintaskId1 = tm.addMaintask(maintask1);
			int maintaskId2 = tm.addMaintask(maintask2);
			
			Subtask subtask1 = new Subtask("Subtask-1", "Discription", maintaskId1, TaskProgress.NEW);
			Subtask subtask2 = new Subtask("Subtask-2", "Discription", maintaskId2, TaskProgress.NEW);
			tm.addSubtask(subtask1);
			tm.addSubtask(subtask2);
			
			hm.addToHistory(task1);
			hm.addToHistory(task2);
			hm.addToHistory(maintask1);
			hm.addToHistory(maintask2);
			hm.addToHistory(subtask1);
			hm.addToHistory(subtask2);
			
			List<Task> arr = hm.getHistory();
			
			Assertions.assertTrue(arr.size() == 6 &&
					arr.contains(task1) &&
					arr.contains(task2) &&
					arr.contains(maintask1) &&
					arr.contains(maintask2) &&
					arr.contains(subtask1) &&
					arr.contains(subtask2));
		}
	
}
