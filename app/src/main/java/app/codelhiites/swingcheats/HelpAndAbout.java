package app.codelhiites.swingcheats;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HelpAndAbout extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_and_about);
       // Button git = (Button)findViewById(R.id.github);



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.info)
        {
            startActivity(new Intent(this, HelpAndAbout.class));
            return true;
        }
        else if (id == R.id.share)
        {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Wanna increase the score of a new addicted and one of the toughest game Swing Copter, have a look at this https://play.google.com/store/apps/details?id=app.codelhiites.swingcheats |\n sent via Swing Copter Cheats ";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Swing Copter Cheat App");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
            HelpAndAbout.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.bounce_interpolator);
            return true;
        }
        else if (id == R.id.rate)
        {
            Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
            rate.setData(Uri.parse("https://play.google.com/store/apps/details?id=app.codelhiites.swingcheats"));
            startActivity(rate);
            HelpAndAbout.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            return true;
        }
        else if (id == R.id.more)
        {
            Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
            rate.setData(Uri.parse("https://play.google.com/store/apps/developer?id=ITIL+CIC"));
            startActivity(rate);
            HelpAndAbout.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Function for github.
    public void github(View view)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/teamDAPSR/SwingingCheats")));
    }


}
