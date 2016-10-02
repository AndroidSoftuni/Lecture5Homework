package bg.softuni.softuniandroidsql;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.os.AsyncTask;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;
import java.util.ArrayList;

import bg.softuni.softuniandroidsql.db.Grocery;
import bg.softuni.softuniandroidsql.db.GroceryDaoImpl;
import bg.softuni.softuniandroidsql.adapters.GroceriesAdapter;
import bg.softuni.softuniandroidsql.services.DBInteractionService;

public class MainActivity extends AppCompatActivity {

    private Button   btnAddToCart;

    private RecyclerView  recyclerView;
    private List<Grocery> groceries;

    private GroceriesAdapter  groceriesAdapter;
    private BroadcastReceiver addGroceryReceiver;

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter addGroceryIntentFilter = new IntentFilter("add");
        addGroceryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Grocery gr = intent.getParcelableExtra("grocery");
                groceries.add(gr);
                groceriesAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, "ADD " + gr.getGroceryName(), Toast.LENGTH_SHORT).show();
            }
        };
        this.registerReceiver(addGroceryReceiver,addGroceryIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.unregisterReceiver(addGroceryReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceries = new ArrayList<>();

        btnAddToCart     = (Button)   findViewById(R.id.submitGrocery);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DBInteractionService.class);
                startService(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);

        LoadInitialData loadInitialData = new LoadInitialData();
        loadInitialData.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    class LoadInitialData extends AsyncTask<Void,Void,List<Grocery>>{

        @Override
        protected List<Grocery> doInBackground(Void... voids) {
            GroceryDaoImpl groceryDao = new GroceryDaoImpl(MainActivity.this);

            List<Grocery> groceries = groceryDao.listAllGroceries();

            groceryDao.close();
            return groceries;
        }

        @Override
        protected void onPostExecute(List<Grocery> allGroceries) {
            groceries        = allGroceries;
            groceriesAdapter = new GroceriesAdapter(MainActivity.this,groceries);
            recyclerView.setAdapter(groceriesAdapter);
        }
    }
}
