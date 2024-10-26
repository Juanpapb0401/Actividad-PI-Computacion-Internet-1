import PiEstimation.MasterPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;

public class Client {

    public static void main(String[] args) {
        try (Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            ObjectPrx base = communicator.stringToProxy("Master:default -p 10000");
            MasterPrx master = MasterPrx.checkedCast(base);

            if (master == null) {
                throw new Error("Invalid proxy");
            }

            int totalPoints = 100000;
            int workers = 4;
            int piEstimate = master.requestPiEstimation(totalPoints, workers);
            System.out.println("Estimated value of Pi: " + piEstimate);
        }
    }
}
