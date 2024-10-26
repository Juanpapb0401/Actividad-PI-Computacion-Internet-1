import com.zeroc.Ice.Current;
import PiEstimation.Master;
import PiEstimation.WorkerPrx;

import java.util.ArrayList;
import java.util.List;

public class MasterI implements Master {

    private List<WorkerPrx> workers;  // Lista de proxies de trabajadores

    // Constructor
    public MasterI(List<WorkerPrx> workers) {
        this.workers = workers;
    }

    @Override
    public int requestPiEstimation(int totalPoints, int workerCount, Current current) {
        int pointsPerWorker = totalPoints / workerCount;
        int totalPointsInsideCircle = 0;

        // Llamadas sincrónicas a cada trabajador para calcular puntos
        for (WorkerPrx worker : workers) {
            totalPointsInsideCircle += worker.calculatePoints(pointsPerWorker);
        }

        // Retorna la estimación de Pi
        return (int) ((4.0 * totalPointsInsideCircle) / totalPoints);
    }
}
