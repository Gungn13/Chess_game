package zotov.chess.piece;

import zotov.chess.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public interface IBishop {

    default Set<CoordinatesShift> getBishopMoves() {
        {
            Set<CoordinatesShift> result = new HashSet<>();

            for (int i = -7; i <= 7; i++) {
                if (i == 0) continue;

                result.add(new CoordinatesShift(i, i));
            }

            for (int i = -7; i <= 7; i++) {
                if (i == 0) continue;

                result.add(new CoordinatesShift(i, -i));
            }

            return result;
        }
    }
}
