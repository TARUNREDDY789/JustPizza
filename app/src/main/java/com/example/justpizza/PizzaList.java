package com.example.justpizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PizzaList extends AppCompatActivity {
    ListView list_view;
    List<PizzaPojo> pizzaPojos;
    ProgressDialog progressDialog;
    AllPizzasAdapter Adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
        getSupportActionBar().setTitle(" Pizzaz");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = FirebaseFirestore.getInstance();

        pizzaPojos = new ArrayList<>();

        list_view = (ListView) findViewById(R.id.list_view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait data is being Loaded");
        progressDialog.show();

        getdata();
    }

    public void getdata(){
        db.collection("Pizza")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PizzaPojo homeDataPojo = document.toObject(PizzaPojo.class);
                                pizzaPojos.add(homeDataPojo);
                            }
                            Adapter = new AllPizzasAdapter(pizzaPojos,getIntent().getStringExtra("itemname"), getIntent().getStringExtra("description"),PizzaList.this);
                            list_view.setAdapter(Adapter);

                        }
                        else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}