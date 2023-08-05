package zotov.chess;

import zotov.chess.board.Board;
import zotov.chess.board.BoardConsoleRenderer;
import zotov.chess.board.Move;

import java.util.Collections;
import java.util.List;

public class Game {

    private final Board board;

    private final BoardConsoleRenderer renderer = new BoardConsoleRenderer();

    private final List<GameStateChecker> gameStateCheckers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );


    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop(){

        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while(state == GameState.ONGOING){
            renderer.render(board);

            if(colorToMove == Color.WHITE){
                System.out.println("White to move");
            }else{
                System.out.println("Black to move");
            }

            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            board.makeMove(move);

            colorToMove = colorToMove.opposite();
            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);
        System.out.println("Game ended with state = " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker: gameStateCheckers) {
            GameState state = checker.check(board, color);

            if(state != GameState.ONGOING){
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
