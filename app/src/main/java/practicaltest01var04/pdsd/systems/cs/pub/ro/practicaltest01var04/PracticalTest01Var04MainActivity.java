package practicaltest01var04.pdsd.systems.cs.pub.ro.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
    private int total_tries = 0, good_tries = 0, bad_tries = 0;
    public final static String TOTAL_KEY = "total_tries", GOOD_KEY = "good_tries",
        BAD_KEY = "bad_tries",
        TEXT_KEY = "ro.pub.cs.systems.pdsd.practicaltest01var04.notes",
        RESULT_KEY = "activity_result";
    final private static int SECOND_ACTIVITY_REQUEST_CODE = 2015;

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

        Button nav_button = (Button)findViewById(R.id.nav_button);

        nav_button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edit = (EditText)findViewById(R.id.editText);

                Intent intent =
                        new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01Var04SecondaryActivity");
                intent.putExtra(TEXT_KEY, edit.getText().toString());

                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case SECOND_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle data = intent.getExtras();

                    boolean correct = data.getBoolean(RESULT_KEY);
                    total_tries++;
                    if (correct)
                        good_tries++;
                    else
                        bad_tries++;

                    Toast.makeText(getApplicationContext(),
                            "Incercarea a fost " + (correct ? "corecta" : "gresita"),
                            Toast.LENGTH_LONG).show();

                    EditText edit = (EditText)findViewById(R.id.editText);
                    edit.setText("");
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(TOTAL_KEY, total_tries);
        savedInstanceState.putInt(GOOD_KEY, good_tries);
        savedInstanceState.putInt(BAD_KEY, bad_tries);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        total_tries = savedInstanceState.getInt(TOTAL_KEY);
        good_tries = savedInstanceState.getInt(GOOD_KEY);
        bad_tries = savedInstanceState.getInt(BAD_KEY);

        Log.println(Log.DEBUG, "Colocviu", "Total = " + total_tries  + "; good = " + good_tries +
                "; bad = " + bad_tries);
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
