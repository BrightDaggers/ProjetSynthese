package supelec.projet.projetsynthese;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;

public class Test extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener {
    private float i;
    TextView text;
    SeekBar skb;
    Button b;
    EditText etext;

    boolean textedit=false;
    boolean progedit=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        i = 0;

        /*TextView text = new TextView(this);
        text.setText("TurtleMine : Hello");
        setContentView(text);*/

        setContentView(R.layout.test);


        text = (TextView)findViewById(R.id.textView2);
        skb = (SeekBar)findViewById(R.id.seekBar2);
        b = (Button)findViewById(R.id.button);
        etext = (EditText)findViewById(R.id.editText);
        b.setOnClickListener(this);
        skb.setOnSeekBarChangeListener(this);

        etext.setText(Integer.toString(0));
        etext.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:

                break;
            default:
                text.setText("yolo");
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (textedit){
            textedit=false;
            return;
        }

        progedit = true;
        etext.setText(Integer.toString(skb.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_NULL
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (progedit) {
                progedit = false;
                return true;
            }


            if (etext.getText().length() > 0) {
                int tmp = Integer.valueOf(etext.getText().toString()).intValue();



                textedit = true;
                skb.setProgress(tmp);
            }
        }
        return true;
    }
}
