package bg.softuni.softuniandroidsql.services;

import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class StatusReceiver extends BroadcastReceiver {
    public StatusReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = intent.getStringExtra("status");
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }
}
