package bg.softuni.softuniandroidsql.services;

import android.os.IBinder;
import android.app.Service;
import android.widget.Toast;
import android.content.Intent;

import bg.softuni.softuniandroidsql.db.Grocery;
import bg.softuni.softuniandroidsql.db.GroceryDaoImpl;

public class DBInteractionService extends Service {



    public DBInteractionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Toast.makeText(DBInteractionService.this, "Start addition", Toast.LENGTH_SHORT).show();

        Thread tr = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100_000_000; i++) {

                    Grocery grocery = new Grocery("grocery " + i, 13+i);

                    GroceryDaoImpl groceryDao = new GroceryDaoImpl(DBInteractionService.this);

                    if(groceryDao.addGrocery(grocery) != -1){
                        Intent intent1 = new Intent("add").putExtra("grocery",grocery);
                        sendBroadcast(intent1);

//                        Intent statusIntent = new Intent("Status").putExtra("status","Addition of " + grocery.getGroceryName() + " successful");
//                        sendBroadcast(statusIntent);
                    }
                    else {
                        Intent statusIntent = new Intent("Status").putExtra("status","Addition of " + grocery.getGroceryName() + " failed");
                        sendBroadcast(statusIntent);
                    }

                    groceryDao.close();
                }

                stopSelf();
            }
        });
        tr.start();

        return START_STICKY;
    }
}
