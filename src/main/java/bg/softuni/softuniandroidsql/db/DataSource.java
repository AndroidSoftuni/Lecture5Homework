package bg.softuni.softuniandroidsql.db;

import android.util.Log;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created on 01.10.16.
 */
public abstract class DataSource {

    private static final String TAG = DataSource.class.getSimpleName();

    protected SQLiteDatabase db;
    protected DBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        }catch (SQLException e){
            Log.e(TAG, "UNABLE TO OPEN SQL connection" );
        }
    }

    public void close(){
        dbHelper.close();
    }
}
