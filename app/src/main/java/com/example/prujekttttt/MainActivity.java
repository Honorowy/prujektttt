package com.example.prujekttttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Tresc> pytania;
    EditText editText, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.ListView);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Honorowy/pytania_retrofit2/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Tresc>> call = jsonPlaceHolderApi.getTresci();

        call.enqueue(new Callback<List<Tresc>>() {
            @Override
            public void onResponse(Call<List<Tresc>> call, Response<List<Tresc>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Blad: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                pytania = response.body();

                List<String> dane = new ArrayList<>();
                for (Tresc item : pytania){
                    dane.add(item.id + "\n" + item.imie + "\n" + item.nazwisko + "\n" + item.email + "\n" + item.rola);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dane);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Tresc>> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String subject, content, to_email = "";
                subject = editText.getText().toString();
                content = editText2.getText().toString();
                for (Tresc item : pytania){
                    to_email = item.email;
                }
                if (to_email.equals("") && subject.equals("") && content.equals("")){
                    Toast.makeText(MainActivity.this, "Puste", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendEmail(subject, content, to_email);
                }
            }
        });
    }
    public void sendEmail(String subject, String content, String to_email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose email client:"));
    }
}