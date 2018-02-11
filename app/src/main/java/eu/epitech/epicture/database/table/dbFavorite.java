package eu.epitech.epicture.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

import eu.epitech.epicture.CardImage;
import eu.epitech.epicture.MainActivity;

/**
 * Created by Louis Giraud on 11/02/2018.
 */
public class dbFavorite implements BaseColumns {

    public static final String TABLE = "Favorite";

    public static final String COL_FAVORITE_URL = "url";

    public static String getSqlCreateTable() {
        String sql = "CREATE TABLE " + TABLE + " ( " +
                _ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                COL_FAVORITE_URL + " text NOT NULL UNIQUE" +
                ");";
        return sql;
    }

    public static String getSqlDropTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        return sql;
    }

    public static void getAllUrl(ArrayList<CardImage> UrlList) {
        SQLiteDatabase database = MainActivity.database.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                UrlList.add(new CardImage(cursor.getString(cursor.getColumnIndex(COL_FAVORITE_URL))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
    }

    public static void addUrl(String url) {
        Log.i("FAVORITE ADD", url);
        SQLiteDatabase database = MainActivity.database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE_URL, url);
        database.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        database.close();
    }

    public static void delUrl(String url) {
        Log.i("FAVORITE DEL", url);
        SQLiteDatabase database = MainActivity.database.getWritableDatabase();
        database.delete(TABLE, COL_FAVORITE_URL + " = ?", new String[] {url});
        database.close();
    }

    public static int checkFavorite(String url) {
        ArrayList<CardImage> favoriteList = new ArrayList<>();
        getAllUrl(favoriteList);
        int state = 0;
        for (CardImage image : favoriteList) {
            if (image.getUrl() == url)
                state = 1;
        }
        Log.i("dbFavorite Check", "state : " + state + " | url : " + url);
        return state;
    }
}
