package fri.uni_lj.si.taskManagementService.api;


import fri.uni_lj.si.taskManagementService.model.Board;
import fri.uni_lj.si.taskManagementService.model.Task;
import fri.uni_lj.si.taskManagementService.service.BoardService;
import fri.uni_lj.si.taskManagementService.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/board/{boardId}/task")
    public List<Task> getAllTasksForBoard(@PathVariable Long boardId) {
        return taskService.getAllTasksForBoard(boardId);
    }

    @GetMapping("/task/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/board/{boardId}/task")
    public Board addTaskToBoard(@RequestBody Task newTask, @PathVariable Long boardId) {
        return taskService.addTaskToBoard(newTask, boardId);
    }

    @PostMapping("/task/{taskId}")
    public Task editTask(@RequestBody Task newTask, @PathVariable Long taskId) {
        return taskService.editTask(newTask, taskId);
    }

    @DeleteMapping("/task/{taskId}")
    public String deleteTask(@PathVariable Long taskId ) {
        return taskService.deleteTask(taskId);
    }

}
