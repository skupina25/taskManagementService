package fri.uni_lj.si.taskManagementService.service;

import fri.uni_lj.si.taskManagementService.model.Board;
import fri.uni_lj.si.taskManagementService.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    public List<Board> getAllBoards() {
        List<Board> boards = repository.findAll();

        return boards;
    }

    public Board getBoardById(Long id) {
        Optional<Board> board = repository.findById(id);

        if(board.isPresent()){
            return board.get();
        }

        return null;
    }

    public Board addBoard(Board newBoard) {
        Board board = repository.save(newBoard);
        return board;
    }

    public Board editBoard(Board newBoard, Long id) {
        Optional<Board> board = repository.findById(id);

        if(board.isPresent()) {
           Board currentBoard = board.get();

           if(newBoard.getDescription() != null) {
               currentBoard.setDescription(newBoard.getDescription());
           }

           if(newBoard.getTitle() != null) {
               currentBoard.setTitle(newBoard.getTitle());
           }

           return repository.save(currentBoard);
        }

        return null;
    }

    public String deleteBoard(Long id) {
        Optional<Board> board = repository.findById(id);

        if(board.isPresent()) {
            repository.deleteById(id);
            return "Board " + id + " deleted.";
        }
        else {
            return "Board " + id + " not found";
        }
    }


}
