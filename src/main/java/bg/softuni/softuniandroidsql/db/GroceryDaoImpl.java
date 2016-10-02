package bg.softuni.softuniandroidsql.db;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;

import java.util.List;
import java.util.ArrayList;

/**
 * Created on 01.10.16.
 */
public class GroceryDaoImpl extends DataSource implements GroceryDao {

    public GroceryDaoImpl(Context context) {
        super(context);
    }

    @Override
    public long addGrocery(Grocery grocery) {
        ContentValues values = new ContentValues();
        values.put("name",grocery.getGroceryName());
        values.put("price", grocery.getGroceryPrice());
        return db.insert("groceries",null,values);
    }

    @Override
    public List<Grocery> listAllGroceries() {
        List<Grocery> groceries = new ArrayList<>();

        String[] columns = {"name","price"};

        Cursor cursor = db.query("groceries", columns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Grocery grocery = new Grocery();

            grocery.setGroceryName(cursor.getString(cursor.getColumnIndex("name")));
            grocery.setGroceryPrice(cursor.getDouble(cursor.getColumnIndex("price")));

            groceries.add(grocery);
            cursor.moveToNext();
        }
        cursor.close();
        return groceries;
    }
}
