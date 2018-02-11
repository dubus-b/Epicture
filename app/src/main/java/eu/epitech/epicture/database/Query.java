package eu.epitech.epicture.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import eu.epitech.epicture.database.table.*;

/**
 * Created by Louis Giraud on 11/02/2018.
 */

public class Query extends SQLiteOpenHelper {

    public Query(Context context) {
        super(context, InfoDB.DB_NAME, null, InfoDB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(dbAccount.getSqlCreateTable());
        sqLiteDatabase.execSQL(dbImgurAccount.getSqlCreateTable());
        sqLiteDatabase.execSQL(dbFavorite.getSqlCreateTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(dbAccount.getSqlDropTable());
        sqLiteDatabase.execSQL(dbImgurAccount.getSqlDropTable());
        sqLiteDatabase.execSQL(dbFavorite.getSqlDropTable());
        onCreate(sqLiteDatabase);
    }
}
