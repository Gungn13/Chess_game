package zotov.chess;

import zotov.chess.board.Board;
import zotov.chess.board.BoardConsoleRenderer;
import zotov.chess.board.BoardFactory;
import zotov.chess.board.Move;
import zotov.chess.piece.King;
import zotov.chess.piece.Piece;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputCoordinates {

    private static final Scanner scanner = new Scanner(System.in);

    public static Coordinates input(){
        while(true){
            System.out.println("Please enter coordinates. Example: a1.");

            String move = scanner.nextLine();

            if(move.length() != 2) {
                System.out.println("Invalid format. Please enter valid coordinates! Example: a1.");
                continue;
            }

            char fileChar = move.charAt(0);
            char rankChar = move.charAt(1);

            if(!Character.isLetter(fileChar)){
                System.out.println("Invalid format. Please enter valid coordinates! Example: a1.");
                continue;
            }

            if(!Character.isDigit(rankChar)){
                System.out.println("Invalid format. Please enter valid coordinates! Example: a1.");
                continue;
            }

            int rank = Character.getNumericValue(rankChar);
            if(rank < 1 || rank >  8){
                System.out.println("Invalid format. Please enter valid coordinates! Example: a1.");
                continue;
            }
            File file = File.fromChar(fileChar);
            if(file == null){
                System.out.println("Invalid format. Please enter valid coordinates! Example: a1.");
                continue;
            }

            return new Coordinates(file, rank);
        }


    }

    public static Coordinates inputPieceCoordinatesForColor(Color color, Board board){
        while(true){
            System.out.println("Enter coordinates to piece for move");
            Coordinates coordinates = input();

            if(board.isSquareEmpty(coordinates)){
                System.out.println("Empty square! Entry piece on the board");
                continue;
            }

            Piece piece = board.getPiece(coordinates);
            if(piece.color != color){
                System.out.println("Wrong color");
                continue;
            }

            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquares(board);

            if(availableMoveSquare.size() == 0){
                System.out.println("No available move");
                continue;
            }

            return coordinates;
        }
    }

    public static Coordinates inputAvailableSquare(Set<Coordinates> coordinates){
        while(true){
            System.out.println("Enter your move for selected piece!");
            Coordinates input = input();

            if(!coordinates.contains(input)){
                System.out.println("Non-available square!");
                continue;
            }

            return input;
        }
    }

    public static Move inputMove(Board board, Color color, BoardConsoleRenderer renderer){
        while(true) {
            Coordinates sourceCoordinates = InputCoordinates.inputPieceCoordinatesForColor(
                    color, board
            );

            Piece piece = board.getPiece(sourceCoordinates);
            Set<Coordinates> availableMoveSquare = piece.getAvailableMoveSquares(board);

            renderer.render(board, piece);
            Coordinates targetCoordinates = InputCoordinates.inputAvailableSquare(availableMoveSquare);

            Move move = new Move(sourceCoordinates, targetCoordinates);

            if (checkIfKingUnderAttackAfterMove(board, color, move)) {
                System.out.println("Your king is under attack!");
                continue;
            }

            return move;
        }

    }

    private static boolean checkIfKingUnderAttackAfterMove(Board board, Color color, Move move) {
         Board copy = new BoardFactory().copyBoard(board);
         copy.makeMove(move);
         Piece king = copy.getPiecesByColor(color).stream().filter(piece -> piece instanceof King).findFirst().get();
         return copy.isSquareAttackedByColor(king.coordinates, color.opposite());
    }
}
