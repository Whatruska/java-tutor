package thirdLesson.planes;

import java.util.Stack;

public class PlanesManagerImpl implements PlanesManager {
    private Stack<String> stack = new Stack<>();
    private final int MAX_PLANES_PLACES = 5;

    @Override
    public void putPlane(String planeNumber) throws Exception {
        if (stack.size() < MAX_PLANES_PLACES) {
            stack.push(planeNumber);
            System.out.println("Самолет " + planeNumber + " заехал на стоянку");
        } else {
            throw new Exception("Стоянка заполнена");
        }
    }

    @Override
    public void exitAllPlanes() {
        while (!stack.isEmpty()) {
            String number = stack.pop();
            System.out.println("Самолет " + number + " выехал со стоянки");
        }
        System.out.println("Стоянка очищена");
    }

    @Override
    public void exitLast() throws Exception {
        if (!stack.isEmpty()) {
            String number = stack.pop();
            System.out.println("Самолет " + number + " выехал со стоянки");
        } else {
            throw new Exception("Выходить некому, ты шо, пьян?");
        }
    }
}
