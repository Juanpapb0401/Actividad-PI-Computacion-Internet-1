import com.zeroc.Ice.Current;
import PiEstimation.Master;
import PiEstimation.WorkerPrx;

import java.util.List;

public class MasterI implements Master {
    private List<WorkerPrx> workers;

    public MasterI(List<WorkerPrx> workers) {
        this.workers = workers;
    }

    @Override
    public int requestPiEstimation(int totalPoints, int workerCount, Current current) {
        int pointsPerWorker = totalPoints / workerCount;
        int totalPointsInsideCircle = 0;

        for (WorkerPrx worker : workers) {
            totalPointsInsideCircle += worker.calculatePoints(pointsPerWorker);
        }

        return totalPointsInsideCircle;
    }
}