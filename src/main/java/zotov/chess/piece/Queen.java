package zotov.chess.piece;

import zotov.chess.Color;
import zotov.chess.Coordinates;
import zotov.chess.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public class Queen extends LongRangePiece implements IRook,IBishop {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> result = getBishopMoves();
        result.addAll(getRookMoves());

        return result;
    }
}
