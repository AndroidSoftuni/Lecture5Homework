package bg.softuni.softuniandroidsql.db;

import android.util.Log;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 01.10.16.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, "grocery.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String createTAbleGroceries = "create table if not exists groceries(_id integer primary key autoincrement not null, name text not null, price real not null)";
            db.execSQL(createTAbleGroceries);
        }catch (SQLException e){
            Log.e(TAG, "UNABLE TO CREATE TABLE: " + e.getMessage() );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableGroceries = "drop table if exists groceries";
        db.execSQL(dropTableGroceries);
        String createTAbleGroceries = "create table if not exists groceries(_id integer primary key autoincrement not null)";
        db.execSQL(createTAbleGroceries);
    }
}
