import PiEstimation.MasterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;

public class Client {
    public static void main(String[] args) {
        try (Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            ObjectPrx base = communicator.stringToProxy("Master:default -h 192.168.1.19 -p 10000");
            MasterPrx master = MasterPrx.checkedCast(base);

            if (master == null) {
                throw new Error("Invalid proxy");
            }

            int totalPoints = 100000000;
            int workers = 1000;

            System.out.println("Requesting Pi estimation...");
            int result = master.requestPiEstimation(totalPoints, workers);
            double piEstimate = 4.0 * result / totalPoints;
            System.out.println("Estimated value of Pi: " + piEstimate);
        }
    }
}