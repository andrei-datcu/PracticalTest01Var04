package practicaltest01var04.pdsd.systems.cs.pub.ro.practicaltest01var04;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class PracticalTest01Var04MainActivity extends ActionBarActivity {

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText edit = (EditText)findViewById(R.id.editText);

            if (!edit.getText().toString().isEmpty())
                edit.append(",");
            edit.append(((Button) v).getText());
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private int total_tries = 0, good_tries = 0;
    private final String TOTAL_KEY = "total_tries", GOOD_KEY = "good_tries";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        ViewGroup main_layout = (ViewGroup) findViewById(R.id.buttons_layout);

        for (int i = 0; i < main_layout.getChildCount(); ++i)
            if (main_layout.getChildAt(i) instanceof Button) {
                Button b = (Button) main_layout.getChildAt(i);
                b.setOnClickListener(buttonClickListener);
            }
    }

    @Override
    protected  void onStart() {

        Log.println(Log.DEBUG, "Colocviu", "Total = " + total_tries  + "; good = " + good_tries +
                "; bad = " + (total_tries - good_tries));
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(TOTAL_KEY, total_tries);
        savedInstanceState.putInt(GOOD_KEY, good_tries);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        total_tries = savedInstanceState.getInt(TOTAL_KEY);
        good_tries = savedInstanceState.getInt(GOOD_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var04_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
