package pl.akademiakodu.lukaszkolacz.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView cars;
    Button addCar;
    Button removeCars;


    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cars = (TextView) findViewById(R.id.textCars);
        addCar = (Button) findViewById(R.id.buttonAdd);
        removeCars = (Button) findViewById(R.id.buttonRemove);

        database = new MyDatabase(this, 1);

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.registerCar("Ford", "Focus");
            }
        });

        removeCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteAllCars();
            }
        });

        StringBuilder builder = new StringBuilder();

        Cursor cursor = database.getAllCars();
        while (cursor.moveToNext()) {
            builder.append("\nID: " + cursor.getInt(0));
            builder.append("\nMarka: " + cursor.getString(1));
            builder.append("\nModel: " + cursor.getString(2));
            builder.append("\n---------------------------");
        }
        cars.setText(builder.toString());
    }
}
