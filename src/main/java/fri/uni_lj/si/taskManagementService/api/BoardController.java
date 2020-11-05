package fri.uni_lj.si.taskManagementService.api;

import fri.uni_lj.si.taskManagementService.model.Board;
import fri.uni_lj.si.taskManagementService.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }

    @PostMapping("/board")
    public Board addBoard(@RequestBody Board newBoard) {
        return boardService.addBoard(newBoard);
    }

    @GetMapping("/board/{boardId}")
    public Board getBoardById(@PathVariable Long boardId ) {
        return boardService.getBoardById(boardId);
    }

    @GetMapping("/board")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();

    }

    @PostMapping("/board/{boardId}")
    public Board editBoard(@RequestBody Board newBoard, @PathVariable Long boardId) {
        return boardService.editBoard(newBoard, boardId);
    }

    @DeleteMapping("/board/{boardId}")
    public String deleteBoard(@PathVariable Long boardId ) {
        return boardService.deleteBoard(boardId);
    }

}

