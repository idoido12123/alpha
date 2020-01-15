package com.example.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.alpha.FBref.refList;

public class Main2Activity extends AppCompatActivity {
    String text2;
    EditText et1;
    ListView lv1;
    ArrayList<String> IdoList = new ArrayList<String>();
    String st1;
    Intent go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et1=(EditText)findViewById(R.id.editText3);
        lv1=(ListView) findViewById(R.id.listview);

        refList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = ds.getValue(String.class);
                //Log.d("Main2Activity", "Value is: " + value);

                IdoList.clear();
                for (DataSnapshot data : ds.getChildren()){
                    st1=(String) data.getValue();
                    IdoList.add(st1);
                }
                ArrayAdapter<String> adp = new ArrayAdapter<String>(Main2Activity.this,R.layout.support_simple_spinner_dropdown_item, IdoList);
                lv1.setAdapter(adp);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main2Activity", "Failed to read value.", error.toException());
            }
        });








        }

    public void click1 (View view){
        text2=et1.getText().toString();
        refList.child(text2).setValue(text2);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    public boolean onOptionsItemSelected(MenuItem Item){
        String st=Item.getTitle().toString();
        if(st.equals("Auth")){
            go = new Intent(this, MainActivity.class);
            startActivity(go);
        }
        if (st.equals("RTDB")){
            go = new Intent(this, Main2Activity.class);
            startActivity(go);
        }
        return true;
    }


}
