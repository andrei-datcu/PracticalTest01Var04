package practicaltest01var04.pdsd.systems.cs.pub.ro.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by andrei on 3/31/15.
 */
public class PracticalTest01Var04SecondaryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // intent from parent
        Intent intentFromParent = getIntent();
        Bundle data = intentFromParent.getExtras();

        String text = data.getString(PracticalTest01Var04MainActivity.TEXT_KEY);

        Intent intentToParent = new Intent();

        intentToParent.putExtra(PracticalTest01Var04MainActivity.RESULT_KEY,
                text.equals("Do,Mi,Sol,Do'"));

        setResult(RESULT_OK, intentToParent);
        finish();
    }
}