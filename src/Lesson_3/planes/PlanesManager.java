package Lesson_3.planes;

public interface PlanesManager {
    void putPlane(String planeNumber) throws Exception;
    void exitAllPlanes();
    void exitLast() throws Exception;
}
