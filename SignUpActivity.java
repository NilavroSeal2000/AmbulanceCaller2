package com.example.urgent_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


   public EditText e4,e5,e6,e7;

    public FirebaseAuth auth;
    public ProgressDialog dialog;
    public DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        e4=findViewById(R.id.name);
        e5=findViewById(R.id.email_up);
        e6=findViewById(R.id.password_up);
        e7=findViewById(R.id.phone_up);

        auth=FirebaseAuth.getInstance();
        dialog = new  ProgressDialog(this);


    }


    public void signupUser(View V)
    {
        dialog.setMessage("Registering... Please wait");
        dialog.show();

        String name=e4.getText().toString();
        String email=e5.getText().toString();
        String password=e6.getText().toString();
        String phone=e7.getText().toString();

        if(name.equals("")|| email.equals("") && password.equals("") && phone.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Field cannot be empty!",Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(),"User Successfully Registered!",Toast.LENGTH_SHORT).show();

                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                Users user_object=new Users(e4.getText().toString(),e5.getText().toString(),e6.getText().toString(),e7.getText().toString());
                                FirebaseUser firebaseUser = auth.getCurrentUser();

                                //assert firebaseUser != null;
                                databaseReference.child(firebaseUser.getUid()).setValue(user_object)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(),"User Data saved",Toast.LENGTH_LONG).show();
                                                    Intent i=new Intent(SignUpActivity.this,MainPageActivity.class);
                                                    startActivity(i);
                                                    finish();

                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(),"User Data could not be saved",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }
                            else
                            {   dialog.hide();
                                Toast.makeText(getApplicationContext(),"User could not be registerd",Toast.LENGTH_SHORT).show();}
                        }
                    });
    }


}}
