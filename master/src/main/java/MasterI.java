import com.zeroc.Ice.Current;
import PiEstimation.Master;  // La interfaz generada por el archivo .ice
import PiEstimation.WorkerPrx;  // El proxy de los trabajadores

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class MasterI implements Master {

    private final List<WorkerPrx> workers;

    public MasterI(List<WorkerPrx> workers) {
        this.workers = workers;
    }

    @Override
    public int requestPiEstimation(int totalPoints, int workerCount, Current current) {
        int pointsPerWorker = totalPoints / workerCount;
        int pointsInsideCircle = 0;

        List<Future<Integer>> futures = new ArrayList<>();

        // Asignar tareas a los trabajadores
        for (WorkerPrx worker : workers) {
            futures.add(worker.begin_calculatePoints(pointsPerWorker));  // Llamada asíncrona
        }

        // Recolectar resultados
        for (Future<Integer> future : futures) {
            try {
                pointsInsideCircle += future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return pointsInsideCircle;
    }
}
