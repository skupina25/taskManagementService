package fri.uni_lj.si.taskManagementService.repository;

import fri.uni_lj.si.taskManagementService.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
