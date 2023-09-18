package org.prison.silicon;

public enum FacilityList {
    LOW_SECURITY_UNIT("Low Security Unit"),
    MEDIUM_SECURITY_UNIT("Medium Security"),
    HIGH_SECURITY_UNIT("High Security Unit"),
    KITCHEN("Kitchen"),
    WORK_AREA("Work Area"),
    YARD("Yard");

    private String displayName;

    FacilityList(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}