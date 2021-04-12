  package com.example.magicsquare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

  public class MainActivity extends AppCompatActivity {
    EditText et11,et12,et13,et21,et22,et23,et31,et32,et33;
    TextView suncol1,suncol2,suncol3,sumLine1,sumLine2,sumLine3,verify;
    int val11,val12,val13,val21,val22,val23,val31,val32,val33;
    Button btnNewGame,btnSubmit,btnResume;
    Chronometer chronometer;
    Random alea;
    String state;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        alea=new Random(System.currentTimeMillis());
        verify=findViewById(R.id.verify);
        chronometer=findViewById(R.id.chronometer);
        state = new String("");

        btnNewGame=findViewById(R.id.btnNewGame);
        btnResume=findViewById(R.id.btnResume);
        btnSubmit=findViewById(R.id.btnSubmit);
        et11 = findViewById(R.id.et11);
        et12 = findViewById(R.id.et12);
        et13 = findViewById(R.id.et13);
        et21 = findViewById(R.id.et21);
        et22 = findViewById(R.id.et22);
        et23 = findViewById(R.id.et23);
        et31 = findViewById(R.id.et31);
        et32 = findViewById(R.id.et32);
        et33 = findViewById(R.id.et33);

        suncol1= findViewById(R.id.suncol1);
        suncol2= findViewById(R.id.suncol2);
        suncol3= findViewById(R.id.suncol3);
        sumLine1=findViewById(R.id.sumLine1);
        sumLine2=findViewById(R.id.sumLine2);
        sumLine3=findViewById(R.id.sumLine3);

        init();
    }
    public void submit(View v){
        try {
            int resultLine1 = Integer.parseInt(sumLine1.getText().toString());
            int resultLine2 = Integer.parseInt(sumLine2.getText().toString());
            int resultLine3 = Integer.parseInt(sumLine3.getText().toString());

            int resultCol1 = Integer.parseInt(suncol1.getText().toString());
            int resultCol2  = Integer.parseInt(suncol2.getText().toString());
            int resultCol3  = Integer.parseInt(suncol3.getText().toString());
            if (resultLine1==val11+val12+val13 & resultLine2==val21+val22+val23 & resultLine3==val31+val32+val33 & resultCol1==val21+val22+val32 & resultCol2==val12+val22+val32 & resultCol3==val13+val23+val33 )
            {
                verify.setText("Good, you reslove the magic square!");
                btnNewGame.setEnabled(true);
                btnResume.setEnabled(false);
                chronometer.stop();
            }else{
                verify.setText("TryAgain");
                btnNewGame.setEnabled(true);
                btnResume.setEnabled(true);
                btnSubmit.setEnabled(false);

            }

        } catch(NumberFormatException nfe) {
          // message somewhere
            verify.setText("integer only...");
       }

    }
    public  void newGame(View v){
        btnNewGame.setEnabled(false);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("The help is not the best way t o resolve this magic square :)");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        init();
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        btnNewGame.setEnabled(true);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
    public void resume(View v){
        btnSubmit.setEnabled(true);
        btnNewGame.setEnabled(false);
        btnResume.setEnabled(false);
    }
    public void quit(View v){
        this.finish();
    }
    public void help(View v){
       if (count==0){
           et11.setText(String.format("%d",val11));
           et11.setFocusable(false);
       }
        if (count==1){
            et12.setText(String.format("%d",val12));
            et12.setFocusable(false);
        }
        if (count==2){
            et13.setText(String.format("%d",val13));
            et13.setFocusable(false);
        }
        if (count==3){
            et21.setText(String.format("%d",val21));
            et21.setFocusable(false);
        }
        if (count==4){
            et22.setText(String.format("%d",val22));
            et22.setFocusable(true);
        }
        if (count==5){
            et23.setText(String.format("%d",val23));
            et23.setFocusable(false);
            count++;
        }
        if (count==6){
            et31.setText(String.format("%d",val31));
            et31.setFocusable(true);
        }
        if (count==7){
            et32.setText(String.format("%d",val32));
            et32.setFocusable(false);
        }
        if (count==8){
            et33.setText(String.format("%d",val33));
            et33.setFocusable(false);
        }else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("You can resolve it without more help ...");
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        count++;
    }
    private void init(){
        chronometer.start();
        //Init Variables for out square
        val11=1+alea.nextInt(9);
        val12=1+alea.nextInt(9);
        val13=1+alea.nextInt(9);
        val21=1+alea.nextInt(9);
        val22=1+alea.nextInt(9);
        val23=1+alea.nextInt(9);
        val31=1+alea.nextInt(9);
        val32=1+alea.nextInt(9);
        val33=1+alea.nextInt(9);

        //---------------ROW-------------//
        int sumofLine1=val11+val12+val13;
        int sumofLine2=val21+val22+val23;
        int sumofLine3=val31+val32+val33;

        sumLine1.setText(String.format("%d",sumofLine1));
        sumLine2.setText(String.format("%d",sumofLine2));
        sumLine3.setText(String.format("%d",sumofLine3));

        //---------------Column------------//
        int sumofCol1=val11+val21+val31;
        int sumofCol2=val12+val22+val32;
        int sumofCol3=val13+val23+val33;

        suncol1.setText(String.format("%d",sumofCol1));
        suncol2.setText(String.format("%d",sumofCol2));
        suncol3.setText(String.format("%d",sumofCol3));
    }
      // LIFE CYCLE //

      @Override
      protected void onStart(){
          super.onStart();
          chronometer=findViewById(R.id.chronometer);
          chronometer.start();
          dispalState("++ on Start() +\n");
      }
      @Override
      protected void onPause(){
          super.onPause();
          chronometer=findViewById(R.id.chronometer);
          chronometer.stop();
          dispalState("Clock Pause() \n");
      }
      @Override
      protected void onStop(){
          super.onStop();
          chronometer=findViewById(R.id.chronometer);
          chronometer.stop();
          dispalState("Clock  Stop() ++\n");
      }
      @Override
      protected void onDestroy(){
          super.onDestroy();
          chronometer=findViewById(R.id.chronometer);
          chronometer.stop();
          dispalState("Clock Destroy\n");
      }
      @Override
      protected void  onSaveInstanceState(Bundle data){
          super.onSaveInstanceState(data);
          data.putString("msg",state);
      }
      private void dispalState(String currentState){
          state += currentState;
          Toast.makeText(this, currentState,Toast.LENGTH_LONG).show();//short window that is dissapera on the window
      }
}