package bg.softuni.softuniandroidsql.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import bg.softuni.softuniandroidsql.R;
import bg.softuni.softuniandroidsql.db.Grocery;

/**
 * Created on 02.10.16.
 */
public class GroceriesAdapter extends RecyclerView.Adapter<GroceriesAdapter.CustomVH> {

    private Context context;
    private List<Grocery> groceries;

    public GroceriesAdapter(Context activity, List<Grocery> groceries) {
        this.context = activity;
        this.groceries = groceries;
    }

    @Override
    public CustomVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.grocery_item,parent,false);
        CustomVH customVH = new CustomVH(row);
        return customVH;
    }

    @Override
    public void onBindViewHolder(CustomVH holder, int position) {
        if (position == (groceries.size() -1)){
            holder.txtSeparator.setVisibility(View.GONE);
        }else{
            holder.txtSeparator.setVisibility(View.VISIBLE);
        }
        holder.txtGroceryName.setText(groceries.get(position).getGroceryName());
        holder.txtGroceryPrice.setText(String.valueOf(groceries.get(position).getGroceryPrice()));
    }

    @Override
    public int getItemCount() {
        return groceries.size();
    }

    protected class CustomVH extends RecyclerView.ViewHolder{

        private TextView txtGroceryName;
        private TextView txtGroceryPrice;
        private TextView txtSeparator;

        public CustomVH(View itemView) {
            super(itemView);

            txtGroceryName = (TextView) itemView.findViewById(R.id.txtGroceryName);
            txtGroceryPrice = (TextView) itemView.findViewById(R.id.txtGroceryPrice);
            txtSeparator = (TextView) itemView.findViewById(R.id.separator);
        }
    }
}
