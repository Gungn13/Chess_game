package zotov.chess.board;

import zotov.chess.Color;
import zotov.chess.Coordinates;
import zotov.chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    final String startingFen;
    public HashMap<Coordinates, Piece> pieces = new HashMap<>();

    public List<Move> moves = new ArrayList<>();

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPiece(Coordinates coordinates, Piece piece){
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates){
        pieces.remove(coordinates);
    }

    public void makeMove(Move move){
        Piece piece = getPiece(move.from);

        removePiece(move.from);
        setPiece(move.to,piece);

        moves.add(move);
    }

    public boolean isSquareEmpty(Coordinates coordinates){
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates){
        return pieces.get(coordinates);
    }

    public static boolean isSquareDark(Coordinates coordinates){
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) % 2) == 0;
    }

    public List<Piece> getPiecesByColor(Color oppositeColor) {
        List<Piece> result = new ArrayList<>();

        for (Piece piece : pieces.values()) {
            if(piece.color == oppositeColor){
                result.add(piece);
            }
        }
        return result;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color oppositeColor) {
        List<Piece> oppositePieces = getPiecesByColor(oppositeColor);

        for (Piece piece:oppositePieces){
            Set<Coordinates> attackedSquares = piece.getAttackedSquares(this);

            if(attackedSquares.contains(coordinates)){
                return true;
            }
        }
        return false;
    }


}
