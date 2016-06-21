package com.example.subba.customdialogasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.subba.customdialogasynctask.R.layout.success;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressDialog progressDialog;
    Button startDialog;
    View mdivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDialog = (Button) findViewById(R.id.button);
        startDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(this);

        View dialogView = inflater.inflate(R.layout.promptt, null);

        final AlertDialog alert =  new AlertDialog.Builder(this).create();

        final EditText title = (EditText) dialogView.findViewById(R.id.num);

        Button btnAdd1 = (Button) dialogView.findViewById(R.id.okay);
        Button btnAdd2 = (Button) dialogView.findViewById(R.id.cancel);

        mdivider = dialogView.findViewById(R.id.titleDivider);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String number = title.getText().toString();
                new Task().execute(number);
                alert.dismiss();
            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // btnAdd2 has been clicked
                alert.cancel();
            }
        });

        alert.setView(dialogView);

        alert.show();
    }

    public MainActivity setDividerColor(String colorString) {
        mdivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    class Task extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("please Wait");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            Log.i("enter", "entered");
            int number = Integer.parseInt(params[0]) * 1000;
            Log.i("enter", "entered" +number);
            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Log.i("enter", "entered");
            progressDialog.dismiss();
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            LayoutInflater inflate = LayoutInflater.from((MainActivity.this));
            View dialogVie = inflate.inflate(R.layout.success, null);

            Button btnA = (Button) dialogVie.findViewById(R.id.ok);

            mdivider = dialogVie.findViewById(R.id.titleDivider);

            btnA.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.setView(dialogVie);

           /* alertDialog.setTitle("Successful");
            alertDialog.setMessage("Completed");



            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });*/
            alertDialog.show();

        }

        private Task setDividerColor(String colorString) {
            mdivider.setBackgroundColor(Color.parseColor(colorString));
            return this;
        }


    }

}
