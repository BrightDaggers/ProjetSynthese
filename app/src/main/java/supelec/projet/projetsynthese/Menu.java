package supelec.projet.projetsynthese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chris on 11/04/2018.
 */

public class Menu extends Activity implements View.OnClickListener {
    Button start;
    Button config;
    Button test;
    Button photo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);

        start = (Button)findViewById(R.id.startButton);
        config = (Button)findViewById(R.id.configButton);
        test = (Button)findViewById(R.id.testButton);
        photo = (Button)findViewById(R.id.photoButton);

        start.setOnClickListener(this);
        config.setOnClickListener(this);
        test.setOnClickListener(this);
        photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startButton:
                Intent mainActivity = new Intent(Menu.this, Test.class);
                startActivity(mainActivity);
                break;
            case R.id.configButton:
                Intent configActivity = new Intent(Menu.this, Config.class);
                startActivity(configActivity);
                break;
            case R.id.testButton:
                Intent testActivity = new Intent(Menu.this, Test.class);
                startActivity(testActivity);
                break;
            case R.id.photoButton:
                System.out.println("Photo");
                Intent photoActivity = new Intent(Menu.this, CameraDisplay.class);
                startActivity(photoActivity);
                break;
        }
    }
}
