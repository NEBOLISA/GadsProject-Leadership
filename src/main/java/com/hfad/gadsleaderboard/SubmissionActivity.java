package com.hfad.gadsleaderboard;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.hfad.gadsleaderboard.APIService;
public class SubmissionActivity extends AppCompatActivity {

   private EditText firstNameText,lastNameText,emailText,linkText;
    private AlertDialog mAlertDialog;
    private View mDialogView;
    private Button mbt1;
    private ImageView mbt2;
    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        androidx.constraintlayout.widget.ConstraintLayout layout = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.submission);
        layout.setBackgroundColor(getResources().getColor(android.R.color.black));
        Toolbar mytoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        final Button submitBtn=(Button)findViewById(R.id.button);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateFields(v);
            }
        });
        firstNameText = (EditText) findViewById(R.id.editText1);
         lastNameText = (EditText) findViewById(R.id.editText2);
        emailText = (EditText) findViewById(R.id.editText3);
        linkText = (EditText) findViewById(R.id.editText4);
    }

    void ConfirmationDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView= LayoutInflater.from(v.getContext()).inflate(R.layout.submit_dialog_layout,viewGroup,false);
        mbt1=(Button)mDialogView.findViewById(R.id.send);
        mbt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAlertDialog.dismiss();
                SubmitProject(v);
            }
        } );
        mbt2=(ImageView)mDialogView.findViewById(R.id.exit);
        mbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                Toast.makeText(SubmissionActivity.this,"Submission cancelled",Toast.LENGTH_LONG).show();
            }
        });
        builder.setView(mDialogView);
        mAlertDialog=builder.create();
        mAlertDialog.show();
    }

    void SuccessDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView=LayoutInflater.from(v.getContext()).inflate(R.layout.successful_dialog_layout,viewGroup,false);
        builder.setView(mDialogView);
        mAlertDialog= builder.create();
        mAlertDialog.show();
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        });
    }
    void ValidateFields(View v) {
        if (firstNameText.getText().toString().trim().equals("") || lastNameText.getText().toString().trim().equals("") || emailText.getText().toString().trim().equals("")
                || linkText.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Missing field, all fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }
        ConfirmationDialog(v);
    }

    void FailedDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        mDialogView=LayoutInflater.from(v.getContext()).inflate(R.layout.notsuccessful_dialog_layout,viewGroup,false);
        builder.setView(mDialogView);
        mAlertDialog= builder.create();
        mAlertDialog.show();

    }


    private void SubmitProject(final View v) {
        firstNameText = (EditText) findViewById(R.id.editText1);
        lastNameText = (EditText) findViewById(R.id.editText2);
        emailText = (EditText) findViewById(R.id.editText3);
        linkText = (EditText) findViewById(R.id.editText4);
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String  link = linkText.getText().toString().trim();
        mApiService=ApiUtils.getAPIService();
        mApiService.savePost(firstName,lastName,email,link).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccess()){
                    FailedDialog(v);
                    return;
                }
                SuccessDialog(v);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SubmissionActivity.this,t.getMessage() +", try again ", Toast.LENGTH_LONG).show();
                FailedDialog(v);
            }
        });
    }



}