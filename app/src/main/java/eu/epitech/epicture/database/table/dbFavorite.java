package eu.epitech.epicture.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import eu.epitech.epicture.MainActivity;

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

    public static ArrayList<String> getAllUrl() {
        ArrayList<String> UrlList = new ArrayList<>();
        SQLiteDatabase database = MainActivity.database.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                UrlList.add(cursor.getString(cursor.getColumnIndex(COL_FAVORITE_URL)));
            }
        }
        cursor.close();
        return UrlList;
    }

    public static void addUrl(String url) {
        SQLiteDatabase database = MainActivity.database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE_URL, url);
        database.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        MainActivity.favoriteList.add(url);
    }

    public static void delUrl(String url) {
        SQLiteDatabase database = MainActivity.database.getWritableDatabase();
        database.delete(TABLE, _ID + " = ?", new String[] {url});
        MainActivity.favoriteList.remove(url);
    }

    public static int checkFavorite(String url) {
        if (MainActivity.favoriteList.contains(url))
            return 1;
        return 0;
    }
}
