package com.shabab.ar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrowseActivity extends AppCompatActivity {

    private TableLayout modelTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        modelTable = findViewById(R.id.modelTable);

        String[][] models = {
                {"Apple", "Ball"},
                {"Cat", "Dog"},
                {"Elephant", "Fish"},
                {"Goat", "Hat"},
                {"Ice_Cream", "Jug"},
                {"Kite", "Lion"},
                {"Monkey", "Nest"},
                {"Orange", "Parrot"},
                {"Queen", "Rabbit"},
                {"Sun", "Tiger"},
                {"Umbrella", "Van"},
                {"Watch", "Xylophone"},
                {"Yak", "Zebra"}
        };

        for (String[] modelPair : models) {
            addModelRow(modelPair[0], modelPair[1]);
        }
    }

    private void addModelRow(String modelName1, String modelName2) {
        TableRow tableRow = new TableRow(this);

        // Set TableRow LayoutParams to match parent width and wrap content height
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT
        );
        tableRow.setLayoutParams(params);

        // Add the first model view
        View modelView1 = createModelView(modelName1);
        tableRow.addView(modelView1);

        // Add the second model view
        View modelView2 = createModelView(modelName2);
        tableRow.addView(modelView2);

        // Add the TableRow to the TableLayout
        modelTable.addView(tableRow);
    }


    private View createModelView(final String modelName) {
        LinearLayout modelLayout = new LinearLayout(this);
        modelLayout.setOrientation(LinearLayout.VERTICAL);
        modelLayout.setPadding(16, 16, 16, 16);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(500, 500));
        imageView.setImageResource(getResources().getIdentifier(modelName.toLowerCase(), "drawable", getPackageName()));

        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(modelName);
        textView.setTextSize(16);

        modelLayout.addView(imageView);
        modelLayout.addView(textView);

        modelLayout.setOnClickListener(v -> {
            Intent intent = new Intent(BrowseActivity.this, MainActivity.class);
            intent.putExtra("MODEL_NAME", modelName);
            startActivity(intent);
        });

        return modelLayout;
    }
}
