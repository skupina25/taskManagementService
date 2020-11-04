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

    @GetMapping("/board/{id}")
    public Board getBoardById(@PathVariable Long id ) {
        return boardService.getBoardById(id);
    }

    @GetMapping("/board")
    public List<Board> getBoardById() {
        return boardService.getAllBoards();

    }

    @PostMapping("/board/{id}")
    public Board editBoard(@RequestBody Board newBoard, @PathVariable Long id) {
        return boardService.editBoard(newBoard, id);
    }

    @DeleteMapping("/board/{id}")
    public String deleteBoardById(@PathVariable Long id ) {
        return boardService.deleteBoard(id);
    }

}

