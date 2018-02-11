package eu.epitech.epicture.database.table;

import android.provider.BaseColumns;

/**
 * Created by Louis Giraud on 11/02/2018.
 */

public class dbImgurAccount implements BaseColumns {

    public static final String TABLE = "ImgurAccount";

    public static final String COL_ACCOUNT_ID = "user_id";

    public static String getSqlCreateTable() {
        String sql = "CREATE TABLE " + TABLE + " ( " +
                _ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                COL_ACCOUNT_ID + " integer NOT NULL UNIQUE" +
                ");";
        return sql;
    }

    public static String getSqlDropTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        return sql;
    }
}
