import PiEstimation.Worker;
import com.zeroc.Ice.Current;
import java.util.Random;

public class WorkerI implements Worker {
    private Random random = new Random();

    @Override
    public int calculatePoints(int totalPoints, Current current) {
        int insideCircle = 0;
        for (int i = 0; i < totalPoints; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }
        return insideCircle;
    }
}