package com.example.lr_20;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class RecipientActivity extends AppCompatActivity {

    private static String creatorName = "Кебец Владислав";

    private TextView answ_TextView;
    private ListView letters_ListView;
    private Button share_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        answ_TextView = findViewById(R.id.textView);
        letters_ListView = findViewById(R.id.listView);
        share_Button = findViewById(R.id.button2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name != null && name.equals(creatorName)) {
            setupRightScene();
        } else {
            setupWrongScene();
        }
    }

    private void setupRightScene() {
        answ_TextView.setText("Правильно!");
        letters_ListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, creatorName.split("")));
        share_Button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, creatorName);
            startActivity(intent);
        });
    }

    private void setupWrongScene() {
        answ_TextView.setText("Неправильно :,{");
    }
}