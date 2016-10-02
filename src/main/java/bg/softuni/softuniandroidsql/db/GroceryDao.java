package bg.softuni.softuniandroidsql.db;

import java.util.List;

/**
 * Created on 01.10.16.
 */
public interface GroceryDao {

    public long addGrocery(Grocery grocery);

    public List<Grocery> listAllGroceries();

}
