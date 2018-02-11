package eu.epitech.epicture.database.table;

import android.provider.BaseColumns;

/**
 * Created by Louis Giraud on 11/02/2018.
 */

public class dbImgurAccount implements BaseColumns {

    public static final String TABLE = "ImgurAccount";

    public static final String COL_ACCOUNT_ID = "account_id";

    public static final String COL_REFRESH_TOKEN = "refresh_token";

    public static final String COL_ACCESS_TOKEN = "access_token";

    public static final String COL_ACCOUNT_USERNAME = "account_username";

    public static String getSqlCreateTable() {
        String sql = "CREATE TABLE " + TABLE + " ( " +
                _ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                COL_ACCOUNT_ID + " text NOT NULL, " +
                COL_REFRESH_TOKEN + " text NOT NULL, " +
                COL_ACCESS_TOKEN + " text NOT NULL, " +
                COL_ACCOUNT_USERNAME + " text NOT NULL" +
                ");";
        return sql;
    }

    public static String getSqlDropTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        return sql;
    }
}
