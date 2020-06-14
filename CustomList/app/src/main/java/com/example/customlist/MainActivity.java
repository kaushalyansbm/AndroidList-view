package com.example.customlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ListView lvProduct;
private ProductListAdapter adapter;
private List<Product> mProductList;
DataBaseHelperTwo db;
Button bt;
private String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
     lvProduct=(ListView)findViewById(R.id.listview_product);
     mProductList=new ArrayList<>();
     db=new DataBaseHelperTwo(this);
     mProductList=db.getAll();
     adapter=new ProductListAdapter(getApplicationContext(),mProductList);
     lvProduct.setAdapter(adapter);


        Button bt=(Button)findViewById(R.id.bt);
        bt.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                db.insertData("kamalhjgjh",100,"fuck","not selected");

                mProductList=db.getAll();
                adapter=new ProductListAdapter(getApplicationContext(),mProductList);
                lvProduct.setAdapter(adapter);

            }
        });


        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),view.getTag(),Toast.LENGTH_LONG).show();
           ///Toast.makeText(getApplicationContext(),"DS"+view.getTag(),Toast.LENGTH_LONG).show();
           if(db.updateData(view.getTag().toString())){
               Toast.makeText(getApplicationContext(),"hari",Toast.LENGTH_LONG).show();
           }else{
               Toast.makeText(getApplicationContext(), "ne",Toast.LENGTH_LONG).show();
           }

                mProductList=db.getAll();
                adapter=new ProductListAdapter(getApplicationContext(),mProductList);
                lvProduct.setAdapter(adapter);
            }
        });

    }
}
