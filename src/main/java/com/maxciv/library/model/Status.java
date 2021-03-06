package com.maxciv.library.model;

public enum Status {

    LIBRARY(0, "Library book"),
    EXCHANGE(1, "Exchanging book"),
    ORDER(2, "Ordered book");

    private final int status;
    private final String statusName;

    Status(int status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public int getStatusId() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }

    /**
     * Get the status which corresponds to the given number as returned by
     * {@link Status#getStatusId()}. If the number is an invalid value,
     * then {@link Status#LIBRARY} will be returned.
     *
     * @param status The numeric status value.
     * @return The status.
     */
    public static Status valueOf(int status) {
        try {
            return Status.values()[status];
        } catch (ArrayIndexOutOfBoundsException e) {
            return LIBRARY;
        }
    }

    public static Status valueOfString(String status) {
        switch (status) {
            case "Library book":
                return LIBRARY;
            case "Exchanging book":
                return EXCHANGE;
            case "Ordered book":
                return ORDER;
        }
        return LIBRARY;
    }
}
