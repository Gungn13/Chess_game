package zotov.chess.board;

import zotov.chess.Coordinates;
import zotov.chess.File;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    public static List<Coordinates> getDiagonalsCoordinatesBetween(Coordinates source, Coordinates target){
        List<Coordinates> result = new ArrayList<>();

        int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;
        int rankShift = source.rank < target.rank ? 1 : -1;

        for(
                int fileIndex = source.file.ordinal() + fileShift,
                rankIndex = source.rank + rankShift;

                fileIndex != target.file.ordinal() || rankIndex != target.rank;

                fileIndex += fileShift, rankIndex += rankShift
        ) {
            result.add(new Coordinates(File.values()[fileIndex], rankIndex));
        }
        return result;
    }

    public static List<Coordinates> getVerticalCoordinatesBetween(Coordinates source, Coordinates target){

        List<Coordinates> result = new ArrayList<>();

        int rankShift = source.rank < target.rank ? 1 : -1;

        for(int rankIndex = source.rank + rankShift; rankIndex != target.rank; rankIndex += rankShift) {
            result.add(new Coordinates(source.file, rankIndex));
        }
        return result;
    }

    public static List<Coordinates> getHorizontalCoordinatesBetween(Coordinates source, Coordinates target){
        List<Coordinates> result = new ArrayList<>();

        int fileShift = source.file.ordinal() < target.file.ordinal() ? 1 : -1;


        for(int fileIndex = source.file.ordinal() + fileShift; fileIndex != target.file.ordinal(); fileIndex += fileShift) {
            result.add(new Coordinates(File.values()[fileIndex], source.rank));
        }
        return result;
    }

}
