package com.example.alpha;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPass;
    Button btn1;
    String email;
    String password;
    private FirebaseAuth mAuth;
    Intent go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPass = (EditText) findViewById(R.id.editText2);
        etEmail = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
    }

   //public void onStart() {
   // super.onStart();
  // FirebaseUser currentUser = mAuth.getCurrentUser();

   // }

    public void click(View view) {
        email = etEmail.getText().toString();
        password = etPass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MainActivity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w("MainActivity", "createUserWithEmail:failure");
                            Toast.makeText((MainActivity.this), "Authentication failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

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
