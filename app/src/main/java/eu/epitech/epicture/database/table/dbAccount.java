package eu.epitech.epicture.database.table;

import android.provider.BaseColumns;

/**
 * Created by Louis Giraud on 11/02/2018.
 */

public class dbAccount implements BaseColumns {

    public static final String TABLE = "Account";

    public static final String COL_ACCOUNT_DATE = "subscription_date";
    public static final String COL_ACCOUNT_USTR = "unique_str";
    public static final String COL_ACCOUNT_SERVICE = "service";

    public static String getSqlCreateTable() {
        String sql = "CREATE TABLE account ( " +
                _ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                COL_ACCOUNT_DATE + " datetime, " +
                COL_ACCOUNT_USTR + " unique_str text, " +
                COL_ACCOUNT_SERVICE + " text" +
                ");";
        return sql;
    }

    public static String getSqlDropTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        return sql;
    }
}
