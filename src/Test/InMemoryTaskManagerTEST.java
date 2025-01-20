package Test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.Egor.Malyshev.model.Maintask;
import ru.Egor.Malyshev.model.Subtask;
import ru.Egor.Malyshev.model.Task;
import ru.Egor.Malyshev.model.TaskProgress;
import ru.Egor.Malyshev.service.InMemoryHistoryManager;
import ru.Egor.Malyshev.service.InMemoryTaskManager;

class InMemoryTaskManagerTEST {

	InMemoryTaskManager tm = new InMemoryTaskManager();
	InMemoryHistoryManager hm = new InMemoryHistoryManager();

	// добавить задачу
	@Test
	void addTask_notNullTask_success() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		Assertions.assertTrue(tm.addTask(task) == task.getId());
	};

	@Test
	void addTask_nullTask_failure() {
		Assertions.assertTrue(tm.addTask(null) == -1);
	};

	@Test
	void addTask_nameIsEmpty_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		task.setName("");
		Assertions.assertTrue(tm.addTask(task) == -1);
	}

	@Test
	void addTask_nameIsBlank_failure() {
		Task task = new Task("   ", "Discription", TaskProgress.NEW);
		Assertions.assertTrue(tm.addTask(task) == -1);
	}

	@Test
	void addTask_taskIdIsMoreThanZero_failure() {
		Task task = new Task(1, "Task", "Discription", TaskProgress.NEW);
		Assertions.assertTrue(tm.addTask(task) == -1);
	}

	@Test
	void addTask_taskMapContainsKeyTaskId_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		int id = tm.addTask(task);
		Task newTask = new Task(++id, "newTask", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.addTask(newTask) == -1);
	}

	@Test
	void addTask_taskHasWrongClass_failure() {
		Maintask maintask = new Maintask("Maintask", "discription");
		Assertions.assertTrue(tm.addTask(maintask) == -1);
	}

	// добавить главную задачу
	@Test
	void addMaintask_maintaskIsntNull_succes() {
		Maintask maintask = new Maintask("Maintask", "discription");
		Assertions.assertTrue(tm.addMaintask(maintask) == maintask.getId());
	}

	@Test
	void addMaintask_maintaskIsNull_failure() {
		Assertions.assertTrue(tm.addMaintask(null) == -1);
	}

	@Test

	void addMaintask_nameIsEmpty_failure() {
		Maintask maintask = new Maintask("", "discription");
		Assertions.assertTrue(tm.addMaintask(maintask) == -1);
	}

	@Test
	void addMaintask_nameIsBlank_failure() {
		Maintask maintask = new Maintask("   ", "discription");
		Assertions.assertTrue(tm.addMaintask(maintask) == -1);
	}

	@Test
	void addMaintask_maintaskIdIsMoreThanZero_failure() {
		Maintask task = new Maintask("Maintask", "Discription");
		task.setId(2);
		Assertions.assertTrue(tm.addMaintask(task) == -1);
	}

	@Test
	void addTask_maintaskMapContainsKeyTaskId_failure() {
		Maintask task = new Maintask("Maintask", "Discription");
		tm.addTask(task);
		Maintask newTask = new Maintask(1, "newMaintask", "newDiscription");
		Assertions.assertTrue(tm.addMaintask(newTask) == -1);
	}

	// добавить подзадачу
	@Test
	void addSubtask_notNullSubtask_success() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.addSubtask(subtask) == subtask.getId());
	}

	@Test
	void addSubtask_subtaskIsNull_failure() {
		Assertions.assertTrue(tm.addSubtask(null) == -1);
	}

	@Test
	void addSubtask_nameIsEmpty_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("", "Discription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.addSubtask(subtask) == -1);
	}

	@Test
	void addSubtask_nameIsBlank_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("   ", "Discription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.addSubtask(subtask) == -1);
	}

	@Test
	void addSubtask_subtaskIdIsMoreThanZero_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		subtask.setId(1);
		Assertions.assertTrue(tm.addSubtask(subtask) == -1);
	}

	@Test
	void addSubtask_subtaskMapContainsSubtaskId_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask(1, "Subtask", "Discription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.addSubtask(subtask) == -1);
	}

	// обновить задачу
	@Test
	void updateTask_notNullTask_succes() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(1, "newTask", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == newTask.getId());
	}

	@Test
	void updateTask_nullTask_failure() {
		Assertions.assertTrue(tm.updateTask(null) == -1);
	}

	@Test
	void updateTask_nameIsEmpty_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(1, "", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == -1);
	}

	@Test
	void updateTask_nameIsBlank_failure() {
		Task newTask = new Task(1, "   ", "Discription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == -1);
	}

	@Test
	void updateTask_taskIdIsEqualsZero_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(0, "Task", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == -1);
	}

	@Test
	void updateTask_taskMapDontContainsTaskId_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(2, "Task", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == -1);
	}

	// обновить главную задачу
	@Test
	void updateMaintask_notNullMaintask_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int id = tm.addMaintask(maintask);
		Maintask newMaintask = new Maintask(id, "Maintask", "Discription");
		Assertions.assertTrue(tm.updateMaintask(newMaintask) == 1);
	}

	@Test
	void updateMaintask_nullMaintask_failure() {
		Maintask maintask = null;
		Assertions.assertTrue(tm.addMaintask(maintask) == -1);
	}

	@Test
	void updateMaintask_nameIsEmpty_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int id = tm.addMaintask(maintask);
		Maintask newMaintask = new Maintask(id, "", "Discription");
		Assertions.assertTrue(tm.updateMaintask(newMaintask) == -1);
	}

	@Test
	void updateMaintask_nameIsBlank_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int id = tm.addMaintask(maintask);
		Maintask newMaintask = new Maintask(id, "   ", "Discription");
		Assertions.assertTrue(tm.updateMaintask(newMaintask) == -1);
	}

	@Test
	void updateMaintask_idIsNotEqualsZer0_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		tm.addMaintask(maintask);
		Maintask newMaintask = new Maintask(0, "Maintask", "Discription");
		Assertions.assertTrue(tm.updateMaintask(newMaintask) == -1);
	}

	@Test
	void updateMaintask_maintaskMapDontContainsTaskId_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(0, "Task", "newDiscription", TaskProgress.NEW);
		Assertions.assertTrue(tm.updateTask(newTask) == -1);
	}

	// обновить подзадачу
	@Test
	void updateSubtask_notNullSubtask_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "newSubtask", "newDiscription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == subtaskId);
	}

	@Test
	void updateSubtask_nullSubtask_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Subtask newSubtask = null;
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == -1);
	}

	@Test
	void updateSubtask_nameIsEmpty_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "", "newDiscription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == -1);
	}

	@Test
	void updateSubtask_nameIsBlank_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "   ", "newDiscription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == -1);
	}

	void updateSubtask_subtaskIdIsEqualsZero_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(0, "newSubtask", "newDiscription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == -1);
	}

	@Test
	void updateSubtask_subtaskMapDontContainsSubtaskId_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(++subtaskId, "newSubtask", "newDiscription", maintaskId, TaskProgress.NEW);
		Assertions.assertTrue(tm.updateSubtask(newSubtask) == -1);
	}

	// получить задачу по id
	@Test
	void getTask_taskMapContainsTaskId_succes() throws Exception {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Assertions.assertTrue(tm.getTask(1).getId() == 1);
	}

	@Test
	void getTask_taskMapDontContainsTaskId_failure() {
		Assertions.assertThrows(Exception.class, () -> tm.getTask(1));
	}

	// получить главную задачу по id
	@Test
	void getMaintask_maintaskMapContainsTaskId_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Assertions.assertTrue(tm.getMaintask(maintaskId).getId() == maintaskId);
	}

	@Test
	void getMaintask_maintaskMapDontContainsTaskId_failure() {
		Assertions.assertThrows(Exception.class, () -> tm.getMaintask(1));
	}

	// получить подзадачу по id
	@Test
	void getSubtask_subtaskMapContainsTaskId_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Assertions.assertTrue(tm.getSubtask(subtaskId).getId() == subtaskId);
	}

	@Test
	void getSubtask_subtaskDoNotMapContainsTaskId_failure() {
		Assertions.assertThrows(Exception.class, () -> tm.getSubtask(1));
	}

	// получить список всех задач
	@Test
	void getTasksList_tasksListIsNotEmpty_success() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Assertions.assertTrue(!tm.getTasksList().isEmpty());
	}

	@Test
	void getTasksList_tasksListIsEmptyOrNull_success() {
		Assertions.assertTrue(tm.getTasksList().isEmpty());
	}

	// получить список всех главных задач
	@Test
	void getMaintasksList_maintaskMapIsNotEmpty_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		tm.addMaintask(maintask);
		Assertions.assertTrue(!tm.getMaintasksList().isEmpty());
	}

	@Test
	void getMaintasksList_maintaskMapIsNullOrEmpty_failure() {
		Assertions.assertTrue(tm.getMaintasksList().isEmpty());
	}

	// получить список всех подзадач
	@Test
	void getSubtasksList_subtaskMapIsNotEmpty_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Assertions.assertTrue(!tm.getSubtasksList().isEmpty());
	}

	@Test
	void getSubtasksList_subtaskMapIsEmptyOrNull_failure() {
		Assertions.assertTrue(tm.getSubtasksList().isEmpty());
	}

	// получить список подзадач по главной задаче
	@Test
	void getSubtasksListByMaintask_subtaskMapIsNotEmpty_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Assertions.assertTrue(!tm.getSubtaskListByMaintask(maintaskId).isEmpty());
	}

	// удалить задачу по id
	@Test
	void deleteTaskById_taskMapContainsTaskId_succes() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Assertions.assertTrue(tm.deleteTaskById(1) == 1);
	}

	@Test
	void deleteTaskById_taskMapContainsTaskId_failure() {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Assertions.assertTrue(tm.deleteTaskById(1) == 1);
	}

	// удалить главную задачу по id
	@Test
	void deleteMaintaskById_maintaskMapContainsMaintaskId_succes() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Assertions.assertTrue(tm.deleteMaintaskById(maintaskId) == maintaskId);
	}

	@Test
	void deleteMaintaskById_maintaskMapDontContainsMaintaskId_failure() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		tm.addMaintask(maintask);
		Assertions.assertTrue(tm.deleteMaintaskById(2) == -1);
	}

	// удалить подзадачу по id
	@Test
	void deleteSubtaskById_subtaskMapContainsSubtaskId() {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Assertions.assertTrue(tm.deleteSubtaskById(2) == 2);
	}

	@Test
	void deleteSubtaskById_subtaskMapDontContainsSubtaskId_failure() {
		Assertions.assertTrue(tm.deleteMaintaskById(1) == -1);
	}

	// удалить все задачи из taskMap
	@Test
	void deleteAllTasks_taskMapIsNotNull_success() throws Exception {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Assertions.assertTrue(tm.deleteAllTasks() == 1);
	}

	// удалить все главные задачи
	@Test
	void deleteAllMaintasks_maintaskIsNotNull_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		tm.addMaintask(maintask);
		Assertions.assertTrue(tm.deleteAllMaintasks() == 1);
	}

	// удалить все подзадачи для всех главных задач
	@Test
	void deleteAllSubtasks_subtaskMapIsNotNull_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		tm.addSubtask(subtask);
		Assertions.assertTrue(tm.deleteAllMaintasks() == 1);
	}

	// очистить все хранилища
	@Test
	void clearAllDepos_deposAreNotNull() throws Exception {
		Assertions.assertTrue(tm.clearAllDepos() == 1);
	}

	// отслеживание статуса задачи
	@Test
	void checkTaskProgress_changeTaskProgressFromNEWToIN_PRPGRESS_succes() throws Exception {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(1, "Task", "Discription", TaskProgress.IN_PROGRESS);
		tm.updateTask(newTask);
		Assertions.assertTrue(tm.getTask(1).getTaskProgress() == TaskProgress.IN_PROGRESS);
	}

	@Test
	void checkTaskProgress_changeTaskProgressFromNEWToDone_succes() throws Exception {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(1, "Task", "Discription", TaskProgress.DONE);
		tm.updateTask(newTask);
		Assertions.assertTrue(tm.getTask(1).getTaskProgress() == TaskProgress.DONE);
	}

	@Test
	void checkTaskProgress_changeTaskprogressFromDONEToNEW_succes() throws Exception {
		Task task = new Task("Task", "Discription", TaskProgress.NEW);
		tm.addTask(task);
		Task newTask = new Task(1, "Task", "Discription", TaskProgress.DONE);
		tm.updateTask(newTask);
		Task newTask2 = new Task(1, "Task", "Discription", TaskProgress.NEW);
		tm.updateTask(newTask2);
		Assertions.assertTrue(tm.getTask(1).getTaskProgress() == TaskProgress.NEW);
	}

	// отслеживание статуса главной задачи
	@Test
	void checkTaskProgress_changeMaintaskprogressFromNEWToINPROGRESS_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "newSubtask", "newDiscription", maintaskId,
				TaskProgress.IN_PROGRESS);
		tm.updateSubtask(newSubtask);
		Assertions.assertTrue(tm.getMaintask(maintaskId).getTaskProgress() == TaskProgress.IN_PROGRESS);
	}

	void checkTaskProgress_changeMaintaskprogressFromNEWToDONE_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "newSubtask", "newDiscription", maintaskId, TaskProgress.DONE);
		tm.updateSubtask(newSubtask);
		Assertions.assertTrue(tm.getMaintask(maintaskId).getTaskProgress() == TaskProgress.DONE);
	}

	void checkTaskProgress_changeMaintaskprogressFromDONEToNEW_succes() throws Exception {
		Maintask maintask = new Maintask("Maintask", "Discription");
		int maintaskId = tm.addMaintask(maintask);
		Subtask subtask = new Subtask("Subtask", "Discription", maintaskId, TaskProgress.NEW);
		int subtaskId = tm.addSubtask(subtask);
		Subtask newSubtask = new Subtask(subtaskId, "newSubtask", "newDiscription", maintaskId, TaskProgress.DONE);
		tm.updateSubtask(newSubtask);
		Subtask newSubtask2 = new Subtask(subtaskId, "newSubtask", "newDiscription", maintaskId, TaskProgress.NEW);
		tm.updateSubtask(newSubtask2);
		Assertions.assertTrue(tm.getMaintask(maintaskId).getTaskProgress() == TaskProgress.NEW);
	}
}