package bg.softuni.softuniandroidsql.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 01.10.16.
 */
public class Grocery implements Parcelable{

    private String groceryName;
    private double groceryPrice;

    public Grocery() {
    }

    public Grocery(String groceryName,double groceryPrice) {
        this.setGroceryName(groceryName);
        this.setGroceryPrice(groceryPrice);
    }

    public static final Creator<Grocery> CREATOR = new Creator<Grocery>() {
        @Override
        public Grocery createFromParcel(Parcel in) {
            return new Grocery(in);
        }

        @Override
        public Grocery[] newArray(int size) {
            return new Grocery[size];
        }
    };

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public double getGroceryPrice() {
        return groceryPrice;
    }

    public void setGroceryPrice(double groceryPrice) {
        this.groceryPrice = groceryPrice;
    }

    protected Grocery(Parcel in){
        groceryName = in.readString();
        groceryPrice = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groceryName);
        parcel.writeDouble(groceryPrice);

    }
}
