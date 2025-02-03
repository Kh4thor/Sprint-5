package ru.Egor.Malyshev;

import ru.Egor.Malyshev.interfaces.TaskManager;
import ru.Egor.Malyshev.model.Maintask;

import ru.Egor.Malyshev.model.Subtask;

import ru.Egor.Malyshev.model.Task;

import ru.Egor.Malyshev.model.TaskProgress;
import ru.Egor.Malyshev.service.InMemoryHistoryManager;
import ru.Egor.Malyshev.utils.Managers;

public class Main {

	public static void main(String[] args) throws Exception {

		TaskManager tm = Managers.getDefault();
		
		InMemoryHistoryManager<Task> hm = new InMemoryHistoryManager<Task>();

		System.out.println("-----------------------------------------------");
		System.out.println("ЗАДАЧИ");
		System.out.println("-----------------------------------------------");
	
		// создаем задачи
		Task task1 = new Task("Задача-1", "", TaskProgress.NEW);
		Task task2 = new Task("Задача-2", "", TaskProgress.NEW);
		Task task3 = new Task("Задача-3", "", TaskProgress.NEW);
		tm.addTask(task1);
		tm.addTask(task2);
		tm.addTask(task3);
		tm.getTask(task1.getId()).setTaskProgress(TaskProgress.IN_PROGRESS);
		tm.getTask(task2.getId()).setTaskProgress(TaskProgress.IN_PROGRESS);
		tm.getTask(task3.getId()).setTaskProgress(TaskProgress.IN_PROGRESS);

		tm.updateTask(task1);
		tm.updateTask(task2);
		tm.updateTask(task3);
		tm.getTask(task1.getId());
		tm.getTask(task2.getId());
		tm.getTask(task3.getId());
		
		tm.getHistory().forEach(el->System.out.println(el));

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("ГЛАВНЫЕ ЗАДАЧИ И ПОДЗАДАЧИ");
		System.out.println("-----------------------------------------------");

		// главные задачи
		Maintask Maintask1 = new Maintask("Главная задача-1", "");
		Maintask Maintask2 = new Maintask("Главная задача-2", "");
		Maintask Maintask3 = new Maintask("Главная задача-3", "");

		// добавляем главные задачи в хранилище
		tm.addMaintask(Maintask1);
		tm.addMaintask(Maintask2);
		tm.addMaintask(Maintask3);

		// создаем подзадачи
		Subtask Subtask1 = new Subtask("Подздача-1", "", Maintask1.getId(), TaskProgress.NEW);
		Subtask Subtask2 = new Subtask("Подздача-2", "", Maintask1.getId(), TaskProgress.NEW);
		Subtask Subtask3 = new Subtask("Подздача-3", "", Maintask1.getId(), TaskProgress.NEW);

		tm.addSubtask(Subtask1);
		tm.addSubtask(Subtask2);
		tm.addSubtask(Subtask3);

		Subtask Subtask4 = new Subtask("Подздача-1", "", Maintask2.getId(), TaskProgress.NEW);
		Subtask Subtask5 = new Subtask("Подздача-2", "", Maintask2.getId(), TaskProgress.NEW);
		Subtask Subtask6 = new Subtask("Подздача-3", "", Maintask2.getId(), TaskProgress.NEW);

		tm.addSubtask(Subtask4);
		tm.addSubtask(Subtask5);
		tm.addSubtask(Subtask6);

		Subtask Subtask7 = new Subtask("Подздача-1", "", Maintask3.getId(), TaskProgress.NEW);
		Subtask Subtask8 = new Subtask("Подздача-2", "", Maintask3.getId(), TaskProgress.NEW);
		Subtask Subtask9 = new Subtask("Подздача-3", "", Maintask3.getId(), TaskProgress.NEW);

		tm.addSubtask(Subtask7);
		tm.addSubtask(Subtask8);
		tm.addSubtask(Subtask9);
		tm.addSubtask(Subtask9);
		tm.addSubtask(Subtask9);

		System.out.println("\nпроверка работы добавления и вывода листа главных задач\n");
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));

		System.out.println("\nпроверка работы добавления и вывода листа подзадач\n");
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		System.out.println("\nПРОВЕРКА СМЕНЫ СТАТУСА ГЛАВНОЙ ЗАДАЧИ");

//		 проверяем работу метода обновления подзадачи и контролю статуса главной
//		// задачи на IN_PROGRESS
		Subtask1.setTaskProgress(TaskProgress.IN_PROGRESS);
		tm.updateSubtask(Subtask1);
		System.out.println("\nInProgress");
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		// проверяем работу метода обновления подзадачи и контролю статуса главной
		// задачи на DONE
		Subtask1.setTaskProgress(TaskProgress.DONE);
		Subtask2.setTaskProgress(TaskProgress.DONE);
		Subtask3.setTaskProgress(TaskProgress.DONE);
		tm.updateSubtask(Subtask1);
		tm.updateSubtask(Subtask2);
		tm.updateSubtask(Subtask3);
		System.out.println("\nDone");

		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		// проверяем работу метода обновления подзадачи и контролю статуса главной
		// задачи на NEW

		Subtask1.setTaskProgress(TaskProgress.NEW);
		Subtask2.setTaskProgress(TaskProgress.NEW);
		Subtask3.setTaskProgress(TaskProgress.NEW);

		tm.updateSubtask(Subtask1);
		tm.updateSubtask(Subtask2);
		tm.updateSubtask(Subtask3);

		System.out.println("\nNew");
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		// проверка метода удаления главной задачи

		tm.deleteMaintaskById(5);
		tm.deleteMaintaskById(6);

		System.out.println("\nПРОВЕРКА ОБНОВЛЕНИЯ ГЛАВНОЙ ЗАДАЧИ И ЕЕ ПОДЗАДАЧ");

		// очистить все хранилища
//		tm.clearAllDepos();

		// замена задачи с некорректным id (обновляются name и discription)
		Maintask newDataTask1 = new Maintask(Subtask1.getId(), "Новая главная задача-1INCORRECT", "");
		tm.updateMaintask(newDataTask1);

		// замена задачи с корректным id (обновляются name и discription)
		Maintask newDataTask2 = new Maintask(Maintask1.getId(), "Новая главная задача-1", "");
		tm.updateMaintask(newDataTask2);

		// замена подзадачи с корректным id
		// (обновляются name, discription и taskProgress)
		Subtask newDataSubtask2 = new Subtask(Subtask1.getId(), "Новая подзадача-1", "", Maintask1.getId(),
				TaskProgress.NEW);
		tm.updateSubtask(newDataSubtask2);
		System.out.println();

		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));
		System.out.println();

		// вызываем списки удаленных подзадач и главной задачи - возвращает null
//		System.out.println(tm.getMaintask(5));
//		System.out.println(tm.getMaintask(6));

		System.out.println("\nПРОВЕРКА УДАЛЕНИЯ ГЛАВНОЙ ЗАДАЧИ И ЕЕ ПОДЗАДАЧ\n");
		System.out.println("удаление всех подзадач\n");

		tm.addSubtask(Subtask1);
		tm.addSubtask(Subtask2);
		tm.addSubtask(Subtask3);

		// удаляем все подзадачи
		tm.deleteAllSubtasks();

		// все подзадачи удалены
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		// все задачи удалены
		System.out.println("удаление всех задач");
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		System.out.println();
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

//		tm.addTask(task1);
//		tm.addTask(task2);
//		tm.addTask(task3);

		tm.addMaintask(Maintask1);
		tm.addMaintask(Maintask2);
		tm.addMaintask(Maintask3);

		tm.addSubtask(Subtask1);
		tm.addSubtask(Subtask2);
		tm.addSubtask(Subtask3);

		tm.deleteAllMaintasks();

		System.out.println("here");

		tm.getTasksList().forEach(task -> System.out.println(task));
//		tm.getAllMaintasksList().forEach(Maintask -> System.out.println(Maintask));
//		tm.getAllSubtaskList().forEach(Subtask -> System.out.println(Subtask));

		// очищаем все хранилища

		tm.clearAllDepos();

		System.out.println("\nвсе хранилища очищены");

		tm.getTasksList().forEach(task -> System.out.println(task));
		tm.getMaintasksList().forEach(Maintask -> System.out.println(Maintask));
		tm.getSubtasksList().forEach(Subtask -> System.out.println(Subtask));

		System.out.println("\nИСТОРИЯ ВЫЗОВА ЗАДАЧ");
		System.out.println("TASKS");
		System.out.println();

		Task task101 = new Task("Задача-1", "", TaskProgress.NEW);
		Task task201 = new Task("Задача-2", "", TaskProgress.NEW);
		Task task301 = new Task("Задача-3", "", TaskProgress.NEW);
		Task task4 = new Task("Задача-4", "", TaskProgress.NEW);
		Task task5 = new Task("Задача-5", "", TaskProgress.NEW);
		Task task6 = new Task("Задача-6", "", TaskProgress.NEW);
		Task task7 = new Task("Задача-7", "", TaskProgress.NEW);
		Task task8 = new Task("Задача-8", "", TaskProgress.NEW);
		Task task9 = new Task("Задача-9", "", TaskProgress.NEW);
		Task task10 = new Task("Задача-10", "", TaskProgress.NEW);
		Task task11 = new Task("Задача-11", "", TaskProgress.NEW);
		Task task12 = new Task("Задача-12", "", TaskProgress.NEW);
		Task task13 = new Task("Задача-13", "", TaskProgress.NEW);
		Task task14 = new Task("Задача-14", "", TaskProgress.NEW);

		tm.addTask(task101);
		tm.addTask(task201);
		tm.addTask(task301);
		tm.addTask(task4);
		tm.addTask(task5);
		tm.addTask(task6);
		tm.addTask(task7);
		tm.addTask(task8);
		tm.addTask(task9);
		tm.addTask(task10);
		tm.addTask(task11);
		tm.addTask(task12);
		tm.addTask(task13);
		tm.addTask(task14);

		Maintask Maintask15 = new Maintask("Задача-15", "");
		int id15  = tm.addMaintask(Maintask15);
		System.out.println(id15);
		
		System.out.println(tm.getMaintask(id15));
		
		Subtask task16 = new Subtask("Подзадача -16", "", Maintask15.getId(), TaskProgress.NEW);
		Subtask task17 = new Subtask("Подзадача -17", "", Maintask15.getId(), TaskProgress.NEW);

		tm.addSubtask(task16);
		tm.addSubtask(task17);

		tm.getTask(task101.getId());
		tm.getTask(task201.getId());
		tm.getTask(task301.getId());
		tm.getTask(task4.getId());
		tm.getTask(task5.getId());
		tm.getTask(task6.getId());
		tm.getTask(task7.getId());
		tm.getTask(task8.getId());
		tm.getTask(task9.getId());
		tm.getTask(task10.getId());
		tm.getTask(task11.getId());
		tm.getTask(task12.getId());
		tm.getTask(task13.getId());
		tm.getTask(task14.getId());
		tm.getTask(task14.getId());
		tm.getTask(task14.getId());
		tm.getTask(task14.getId());
		tm.getTask(task14.getId());
		tm.getMaintask(Maintask15.getId());
		tm.getSubtask(task16.getId());
		tm.getSubtask(task17.getId());

		System.out.println("List");
		tm.getSubtasksList().forEach(el -> System.out.println(el));
		System.out.println();

		Subtask sTask = tm.getSubtask(task16.getId());
		System.out.println("Stask");
		System.out.println(sTask);
		System.out.println();

		boolean a = hm.getHistory().isEmpty();
		System.out.println(a);
		hm.getHistory().forEach(el -> System.out.println(el));
		System.out.println("FINISH");
		tm.clearAllDepos();
		Maintask Maintask = new Maintask("Главная задача", "");
		tm.addMaintask(Maintask);
		Subtask Subtask = new Subtask("Подздача", "", Maintask.getId(), TaskProgress.NEW);
		tm.addSubtask(Subtask);

		tm.getMaintasksList().forEach(el -> System.out.println(el));
		tm.getSubtasksList().forEach(el -> System.out.println(el));
	}
}
