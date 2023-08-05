package zotov.chess.board;

import zotov.chess.Color;
import zotov.chess.Coordinates;
import zotov.chess.File;
import zotov.chess.board.Board;
import zotov.chess.piece.Piece;

import java.util.Set;

import static java.util.Collections.emptySet;

public class BoardConsoleRenderer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";

    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_BLACK_SQUARE_BACKGROUND = "\u001B[0;100m";

    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND = "\u001B[45m";

    public void render(Board board, Piece pieceToMove){
        Set<Coordinates> availableMoveSquares = emptySet();

        if(pieceToMove != null){
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }
        for (int rank = 8; rank >= 1; rank--) {
            String line = "";
            for(File file : File.values()){
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighLight = availableMoveSquares.contains(coordinates);

                if(board.isSquareEmpty(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates, isHighLight);
                }else{
                    line += getPieceSprite(board.getPiece(coordinates), isHighLight);
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    public void render(Board board){
        render(board, null);
    }

    private String selectUnicodeSpriteForPiece(Piece piece){
        return switch (piece.getClass().getSimpleName()) {
            case "Pawn" -> "P";
            case "Rook" -> "R";
            case "King" -> "K";
            case "Queen" -> "Q";
            case "Bishop" -> "B";
            case "Knight" -> "N";
            default -> "";
        };
    }


    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighLight){
        // format = background color + font color + text

        String result = sprite;

        if(pieceColor == Color.BLACK){
            result = ANSI_BLACK_PIECE_COLOR + result;
        } else {
            result = ANSI_WHITE_PIECE_COLOR + result;
        }

        if(isHighLight){
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if(isSquareDark){
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        }else{
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }

        return result;
    }

    private String getSpriteForEmptySquare(Coordinates coordinates, boolean isHighLight){
        return colorizeSprite("   ", Color.BLACK, Board.isSquareDark(coordinates), isHighLight);
    }

    private String getPieceSprite(Piece piece, boolean isHighLight){
        return colorizeSprite( " " + selectUnicodeSpriteForPiece(piece) + " ", piece.color, Board.isSquareDark(piece.coordinates), isHighLight);
    }

}
