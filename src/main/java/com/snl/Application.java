package src.main.java.com.snl;

import src.main.java.com.snl.models.Board;
import src.main.java.com.snl.services.BoardService;
import src.main.java.com.snl.services.GameService;
import src.main.java.com.snl.services.ShuffleService;

public class Application {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        Board board = boardService.formBoard();
        GameService gameService = new GameService(board, new ShuffleService(board.getNoOfDices()), boardService.getUsers());
        gameService.startGame();
    }
}
