package com.example.ninthguidedexercise;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView name;
    ArrayAdapter<String> adapter;
    String[] listOfNames = {"Papsi", "Majoy", "Pol", "Che", "Tin", "Lou", "Renz", "Pet", "Roven", "Chan", "Jher"};
    double[] listOfSemGrades = {1.00, 1.50, 2.00, 1.25, 3.00, 5.00, 1.75, 2.25, 3.00, 1.00, 2.25};

    int[] backgroundColors = {
            Color.parseColor("#1E3A8A"),
            Color.parseColor("#FBCFE8"),
            Color.parseColor("#6B7280"),
            Color.parseColor("#FACC15"),
            Color.parseColor("#E9D5FF"),
            Color.parseColor("#047857"),
            Color.parseColor("#3B82F6"),
            Color.parseColor("#93C5FD"),
            Color.parseColor("#881337"),
            Color.parseColor("#FB923C"),
            Color.parseColor("#22C55E"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.lvNameGE9);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfNames);
        name.setAdapter(adapter);

        showSemGrade();
    }

    public void showSemGrade() {
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (listOfNames[i] == null || listOfNames[i].trim().isEmpty()) {
                    showDialog("Invalid Name!", "");
                    return;
                }

                String status = (listOfSemGrades[i] == 5.00) ? "FAILED ❌" : "PASSED ✅";
                String message = "Name: " + listOfNames[i] + "\nSem Grade: " + listOfSemGrades[i] + "\nStatus: " + status;

                showDialog("Student Information", message);
                view.setBackgroundColor(backgroundColors[i]);
            }
        });
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setPadding(50, 40, 50, 40);
        textView.setTextSize(18);
        textView.setTextColor(Color.BLACK);

        builder.setTitle(title)
                .setView(textView)
                .setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getWindow().setLayout(
                (int)(getResources().getDisplayMetrics().widthPixels * 0.90), // 90% of screen width
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }
}
