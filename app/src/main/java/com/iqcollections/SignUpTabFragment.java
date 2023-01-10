package com.iqcollections;
/*
    Code Attribution :
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=tbh9YaWPKKs
    Title Page/Video: Login & Register Android App Using Firebase | Android Studio Authentication Tutorials | Part 1/4
    Author name/tag/channel: SmallAcademy
    Author channel/profile url link: https://www.youtube.com/c/SmallAcademy
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpTabFragment extends Fragment {
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private EditText edtLoginUser, edtLoginPassword, edtRegisterEmail, edtRegisterUser, edtRegisterPass1, edtRegisterPass2;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflator.inflate(R.layout.signup_tab_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        edtRegisterEmail = root.findViewById(R.id.email2);
        edtRegisterUser = root.findViewById(R.id.username);
        edtRegisterPass1 = root.findViewById(R.id.pass2);
        edtRegisterPass2 = root.findViewById(R.id.confirm_pass2);
        btnRegister = root.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


        mAuth = FirebaseAuth.getInstance();
        edtRegisterEmail = root.findViewById(R.id.email2);
        edtRegisterUser = root.findViewById(R.id.username);
        edtRegisterPass1 = root.findViewById(R.id.pass2);
        edtRegisterPass2 = root.findViewById(R.id.confirm_pass2);
        btnRegister = root.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity lg = new LoginActivity();
                String userEmail = edtRegisterEmail.getText().toString();
                String userPass = edtRegisterPass1.getText().toString();
                String userPasscon = edtRegisterPass2.getText().toString();

                if (userEmail.isEmpty() || userPass.isEmpty() || (!userPass.equals(userPasscon))) {
                    Toast.makeText(getActivity(), "You cannot leave the fields blank/Check if your passwords match", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Succesfull Sign Up!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(), "An error has occured" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });


        return root;
    }

    public void register() {
        LoginActivity lg = new LoginActivity();
        String userEmail = edtRegisterEmail.getText().toString();
        String userPass = edtRegisterUser.getText().toString();
        String userPasscon = edtRegisterPass2.getText().toString();
        if (userEmail.isEmpty() || userPass.isEmpty() || (!userPass.equals(userPasscon))) {

        } else {

            mAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {


                        lg.toaster("You may now sign in");

                    } else {
                        lg.toaster("An Error has occurred " + task.getException());
                    }
                }
            });

        }


    }


}
