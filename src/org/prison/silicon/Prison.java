package org.prison.silicon;

class Prison {
    private final String name;
    private boolean lockDown;
    private int inmateCount;
    private int funds;
    private int totalDays;
    private int riskRating;


    private Prison(String name) {
        this.name = name;
    }

    // Business methods
    public void lockdown() {
        // will lock the prison down stopping all movement
    }

    // Getters
    public String getName() {
        return name;
    }

    public boolean isLockDown() {
        return lockDown;
    }

    public void setLockDown(boolean lockDown) {
        this.lockDown = lockDown;
    }

    public int getInmateCount() {
        return inmateCount;
    }

    public int getFunds() {
        return funds;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getRiskRating() {
        return riskRating;
    }
}