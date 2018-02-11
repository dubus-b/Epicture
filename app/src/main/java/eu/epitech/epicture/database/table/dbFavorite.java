package eu.epitech.epicture.database.table;

import android.provider.BaseColumns;

/**
 * Created by Louis Giraud on 11/02/2018.
 */

public class dbFavorite implements BaseColumns {

    public static final String TABLE = "Favorite";

    public static final String COL_FAVORITE_URL = "url";

    public static String getSqlCreateTable() {
        String sql = "CREATE TABLE account ( " +
                _ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                COL_FAVORITE_URL + " text NOT NULL UNIQUE" +
                ");";
        return sql;
    }

    public static String getSqlDropTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        return sql;
    }
}
