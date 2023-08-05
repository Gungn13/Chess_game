package zotov.chess.piece;

import zotov.chess.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IRook {
    default Set<CoordinatesShift> getRookMoves(){
        Set<CoordinatesShift> result = new HashSet<>();
        for (int i = -7; i <= 7 ; i++) {
            if(i == 0) continue;

            result.add(new CoordinatesShift(i, 0));
        }

        for (int i = -7; i <= 7 ; i++) {
            if(i == 0) continue;

            result.add(new CoordinatesShift(0, i));
        }

        return result;
    }
}
