package thirdLesson.planes;

public interface PlanesManager {
    void putPlane(String planeNumber) throws Exception;
    void exitAllPlanes();
    void exitLast() throws Exception;
}
