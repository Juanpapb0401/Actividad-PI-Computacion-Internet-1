import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import PiEstimation.WorkerPrx;

import java.util.ArrayList;
import java.util.List;

public class MasterServer {
    public static void main(String[] args) {
        List<WorkerPrx> workers = new ArrayList<>();
        
        try (Communicator communicator = Util.initialize(args)) {
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MasterAdapter", "default -h 0.0.0.0 -p 10000");
            
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("Worker:default -h 192.168.1.5 -p 10001");
            WorkerPrx worker = WorkerPrx.checkedCast(base);
            if (worker != null) {
                workers.add(worker);
            }
            
            MasterI masterServant = new MasterI(workers);
            adapter.add(masterServant, Util.stringToIdentity("Master"));
            
            adapter.activate();
            
            System.out.println("Master server is running. Waiting for requests...");
            
            communicator.waitForShutdown();
        }
    }
}