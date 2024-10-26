module PiEstimation {
    interface Worker {
        ["ami"] int calculatePoints(int totalPoints);
    }

    interface Master {
        int requestPiEstimation(int totalPoints, int workers);
    }
}
