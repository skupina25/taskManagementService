package fri.uni_lj.si.taskManagementService.service;

import fri.uni_lj.si.taskManagementService.model.Board;
import fri.uni_lj.si.taskManagementService.repository.BoardRepository;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        return boards;
    }

    public Board getBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()){
            return board.get();
        }

        return null;
    }

    public Board addBoard(Board newBoard) {
        Board board = boardRepository.save(newBoard);

        if(board.getTeamId() != null) {

            final String uri = "http://localhost:8082/api/v1/team/{teamId}/boards";
            RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.postForObject(uri, board.getId(), String.class, board.getTeamId());
            System.out.println(response);

        }

        return board;
    }

    public Board editBoard(Board newBoard, Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) {
           Board currentBoard = board.get();

           if(newBoard.getDescription() != null) {
               currentBoard.setDescription(newBoard.getDescription());
           }

           if(newBoard.getTitle() != null) {
               currentBoard.setTitle(newBoard.getTitle());
           }

            if(newBoard.getTeamId() != null) {
                currentBoard.setTeamId(newBoard.getTeamId());

                // edit the board id on teamManagementService

                final String uri = "http://localhost:8082/api/v1/team/{teamId}/boards/{boardId}";
                RestTemplate restTemplate = new RestTemplate();

                String response = restTemplate.postForObject(uri, newBoard.getId(), String.class, newBoard.getTeamId(), currentBoard.getId());
                System.out.println(response);
            }

           return boardRepository.save(currentBoard);
        }

        return null;
    }

    public String deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent()) {
            boardRepository.deleteById(id);
            Board boardObj = board.get();

            if(boardObj.getTeamId() != null) {

                final String uri = "http://localhost:8082/api/v1/team/{teamId}/boards/{boardId}";
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.delete(uri, boardObj.getTeamId(), boardObj.getId());

            }

            return "Board " + id + " deleted.";
        }
        else {
            return "Board " + id + " not found";
        }
    }


}
