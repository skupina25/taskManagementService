package fri.uni_lj.si.taskManagementService.repository;

import fri.uni_lj.si.taskManagementService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndBoardId(Long id, Long boardId);
}
