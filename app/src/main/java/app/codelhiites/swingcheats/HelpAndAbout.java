package app.codelhiites.swingcheats;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

public class HelpAndAbout extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_and_about);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
		{
			try
			{
				getActionBar().setDisplayHomeAsUpEnabled(true);
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
			}
		}
    }

}
