package org.prison.silicon;

public enum SecurityRating {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private String displayName;

    SecurityRating(String rating) {
        this.displayName = rating;
    }

    public String getDisplayName() {
        return displayName;
    }
}