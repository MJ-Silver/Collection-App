package com.iqcollections;
/*
    Code Attribution :
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=tbh9YaWPKKs
    Title Page/Video: Login & Register Android App Using Firebase | Android Studio Authentication Tutorials | Part 1/4
    Author name/tag/channel: SmallAcademy
    Author channel/profile url link: https://www.youtube.com/c/SmallAcademy
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    private EditText email, pass;
    private TextView forgetPass;
    private Button login;
    private float v = 0;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflator.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetPass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                if (userEmail.isEmpty() || userPass.isEmpty()) {


                } else {
                    mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Context context = view.getContext();
                                Toast.makeText(context, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(getContext(), "An Error has occurred, check your username and password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return root;
    }

}
