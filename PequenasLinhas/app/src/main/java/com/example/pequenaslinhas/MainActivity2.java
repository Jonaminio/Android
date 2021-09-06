package com.example.pequenaslinhas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    Button b1;
    ImageView iv;
    boolean flag;
    int images[]={R.drawable.cor1,R.drawable.cor2};
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        iv=(ImageView) findViewById(R.id.caixa1);
        b1=(Button) findViewById(R.id.trocarcor);

        flag=true;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(images[i]);
                i++;
                if(i==2)
                    i=0;
            }
        });
    }

}