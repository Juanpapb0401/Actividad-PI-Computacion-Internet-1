import com.zeroc.Ice.Current;
import PiEstimation.Master;
import PiEstimation.WorkerPrx;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MasterI implements Master {
    private List<WorkerPrx> workers;

    public MasterI(List<WorkerPrx> workers) {
        this.workers = workers;
    }

    @Override
    public int requestPiEstimation(int totalPoints, int workerCount, Current current) {
        // Asegúrate de que workerCount no exceda el tamaño de workers
        if (workerCount > workers.size()) {
            System.out.println("Warning: Requested worker count exceeds available workers. Adjusting to available workers.");
            workerCount = workers.size(); // Ajustar al número de trabajadores disponibles
        }

        int pointsPerWorker = totalPoints / workerCount;
        int totalPointsInsideCircle = 0;
        CountDownLatch latch = new CountDownLatch(workerCount); // Para esperar a que todos terminen
        int[] results = new int[workerCount]; // Almacena resultados de cada trabajador

        // Crear hilos para cada trabajador
        for (int i = 0; i < workerCount; i++) {
            final int index = i; // Necesario para la referencia en el hilo
            new Thread(() -> {
                results[index] = workers.get(index).calculatePoints(pointsPerWorker);
                latch.countDown(); // Indica que este trabajador terminó
            }).start();
        }

        // Espera a que todos los hilos terminen
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Suma todos los resultados
        for (int result : results) {
            totalPointsInsideCircle += result;
        }

        return totalPointsInsideCircle;
    }
}
