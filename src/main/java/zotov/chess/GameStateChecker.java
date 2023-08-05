package zotov.chess;

import zotov.chess.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
