package zotov.chess;

import zotov.chess.board.Board;
import zotov.chess.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.fromFEN(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
        );

        Game game = new Game(board);
        game.gameLoop();

    }
}
