package mobile.computing.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mobile.computing.model.rest.RestTask;
import mobile.computing.project.R;

public class MainActivity extends AppCompatActivity {


    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestTask restTask = new RestTask();
                restTask.execute();
            }
        });
    }
}
