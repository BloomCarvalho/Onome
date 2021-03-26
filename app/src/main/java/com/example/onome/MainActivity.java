package com.example.onome;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onome.models.DatabaseHandler;
import com.example.onome.models.Names;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final List<Names> nameList = new ArrayList<Names>();
    private static ArrayList<String> message = new ArrayList<String>();
    DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
    private boolean valid = true;

    TextView txtTitle;
    EditText txtName1;
    EditText txtName2;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle = findViewById(R.id.title);
        txtName1 = findViewById(R.id.name1);
        txtName2 = findViewById(R.id.name2);
        btnSearch = findViewById(R.id.btnsearch);
    }

    public void getInfo(View view){
        nameList.clear();
        load( txtName1.getText().toString() );
        load( txtName2.getText().toString() );
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!valid){
                    for(int i = 0; i < message.size(); i++) {
                        Toast.makeText(MainActivity.this, message.get(i), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.d("teste", "Nome 0: "+nameList.get(0).getNome());
                    Log.d("teste", "Nome 1: "+nameList.get(1).getNome());
                    if((databaseHandler.CheckIsDataAlreadyInDBorNot("tbNome", "NAME", nameList.get(0).getNome())) == false) {
                        databaseHandler.addName(
                                nameList.get(0).getNome(),
                                nameList.get(0).getRes().get(0).getFrequencia(),
                                nameList.get(0).getRes().get(1).getFrequencia(),
                                nameList.get(0).getRes().get(2).getFrequencia(),
                                nameList.get(0).getRes().get(3).getFrequencia(),
                                nameList.get(0).getRes().get(4).getFrequencia(),
                                nameList.get(0).getRes().get(5).getFrequencia(),
                                nameList.get(0).getRes().get(6).getFrequencia(),
                                nameList.get(0).getRes().get(7).getFrequencia());

                    }
                    if((databaseHandler.CheckIsDataAlreadyInDBorNot("tbNome", "NAME", nameList.get(1).getNome())) == false){
                        databaseHandler.addName(
                                nameList.get(1).getNome(),
                                nameList.get(1).getRes().get(0).getFrequencia(),
                                nameList.get(1).getRes().get(1).getFrequencia(),
                                nameList.get(1).getRes().get(2).getFrequencia(),
                                nameList.get(1).getRes().get(3).getFrequencia(),
                                nameList.get(1).getRes().get(4).getFrequencia(),
                                nameList.get(1).getRes().get(5).getFrequencia(),
                                nameList.get(1).getRes().get(6).getFrequencia(),
                                nameList.get(1).getRes().get(7).getFrequencia());
                    }
                    go();

                }
            }
        }, 3 * 1000);
    }

    public boolean load(String name){
        Log.d("teste", "Está vindo: "+name);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NamesService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NamesService namesService = retrofit.create(NamesService.class);
        Call<List<Names>> names = namesService.listNames(name);
        //noinspection NullableProblems
        names.enqueue(new Callback<List<Names>>() {
            @Override
            public void onResponse(Call<List<Names>> call, Response<List<Names>> response) {
                String Newname = name;

                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Insira dois nomes!", Toast.LENGTH_SHORT).show();
                }else{
                    if(response.body().isEmpty() || Newname.equals(" ") || Newname == null || Newname.equals("")){

                        if(Newname.equals(" ") || (Newname == null) || Newname.equals("")){
                            valid = false;
                            message.add("Insira dois nomes!");
                        }
                        else{
                            valid = false;
                            message.add("\""+Newname+"\" Não encontrado :(");
                        }
                    }
                    else{
                        nameList.add(response.body().get(0));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Names>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Num foi: "+t, Toast.LENGTH_SHORT).show();
            }
        });
        return false;
    }
    public void skip(View view){
        Intent myIntent = new Intent(this, ResultActivity.class);
        this.startActivity(myIntent);
    }
    public void go(){
        Intent myIntent = new Intent(this, ResultActivity.class);
        myIntent.putExtra("Time", 0);
        myIntent.putExtra("Progress", 0);
        this.startActivity(myIntent);
    }
}















































