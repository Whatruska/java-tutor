package thirdLesson.cats;

import java.util.List;
import java.util.Map;

public interface CatManager {
    Map<String, Cat> getNameCatMap();
    Map<Integer, List<Cat>> getAgeCatMap();
    Map<Cat, List<Cat>> getChildrenCatMap();
}
