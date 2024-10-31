import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class WorkerServer {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            // Create object adapter
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("WorkerAdapter", "default -h 0.0.0.0 -p 10001");
            
            // Create and add servant to adapter
            WorkerI workerServant = new WorkerI();
            adapter.add(workerServant, Util.stringToIdentity("Worker"));
            
            // Activate adapter
            adapter.activate();
            
            System.out.println("Worker server is running on port 10001. Waiting for requests...");
            
            // Wait for shutdown
            communicator.waitForShutdown();
        }
    }
}