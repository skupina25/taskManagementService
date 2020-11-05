package fri.uni_lj.si.taskManagementService.service;


import fri.uni_lj.si.taskManagementService.model.Board;
import fri.uni_lj.si.taskManagementService.model.Task;
import fri.uni_lj.si.taskManagementService.repository.BoardRepository;
import fri.uni_lj.si.taskManagementService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardRepository boardRepository;


    public List<Task> getAllTasksForBoard(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);

        if(board.isPresent()) {
            return board.get().getTasks();
        }
        return null;
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()){
            return task.get();
        }

        return null;
    }

    public Board addTaskToBoard(Task newTask, Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);

        if(board.isPresent()) {


            Board boardObj = board.get();
            newTask.setBoard(boardObj);
            List<Task> boardTasksList = boardObj.getTasks();
            boardTasksList.add(newTask);
            boardObj.setTasks(boardTasksList);


            return boardRepository.save(boardObj);

        }
        return null;

    }

    public Task editTask(Task newTask, Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()) {
            Task currentTask = task.get();

            if(newTask.getDescription() != null) {
                currentTask.setDescription(newTask.getDescription());
            }

            if(newTask.getTitle() != null) {
                currentTask.setTitle(newTask.getTitle());
            }

            if(newTask.getLifecycleStep() != null) {
                currentTask.setLifecycleStep(newTask.getLifecycleStep());
            }

            return taskRepository.save(currentTask);
        }

        return null;
    }

    public String deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()) {
            Task taskObj = task.get();
            Board parentBoard = taskObj.getBoard();
            parentBoard.getTasks().remove(taskObj);
            boardRepository.save(parentBoard);

            return "Task " + id + " deleted.";
        }
        else {
            return "Task " + id + " not found";
        }
    }

}
