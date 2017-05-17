package com.codecool.shop.dao;


public enum SQLiteErrorCode {
    UNKNOWN_ERROR(-1, "unknown error"),
    SQLITE_OK(0, "Successful!"),
    SQLITE_ERROR(1, "SQL error or missing database"),
    SQLITE_PERM(3, "Access permission denied"),
    SQLITE_ABORT(4, "Callback routine requested an abort"),
    SQLITE_BUSY(5, "The database file is locked"),
    SQLITE_LOCKED(6, "A table in the database is locked");
    public final int errorNum;
    public final String errorMessage;

    SQLiteErrorCode(int errorNum, String errorMessage)
    {
        this.errorNum = errorNum;
        this.errorMessage = errorMessage;
    }

    public static SQLiteErrorCode getErrorCode(int errorCode)
    {
        for (SQLiteErrorCode each : SQLiteErrorCode.values())
        {
            if (errorCode == each.errorNum)
                return each;
        }
        return UNKNOWN_ERROR;
    }

    @Override
    public String toString() {
        return "ErrorsSqlite{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
