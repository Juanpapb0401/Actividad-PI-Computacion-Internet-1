module PiEstimation {
    interface Worker {
        int calculatePoints(int totalPoints);
    }

    interface Master {
        int requestPiEstimation(int totalPoints, int workers);
    }
}