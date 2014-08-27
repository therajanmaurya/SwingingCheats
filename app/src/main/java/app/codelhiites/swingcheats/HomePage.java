package app.codelhiites.swingcheats;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomePage extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

		// For setting background opacity.
		Drawable background = getResources().getDrawable(R.drawable.background);
		background.setAlpha(100);

		// Setting layout based on root access.
		if(Functions.isRooted())
		{
            if (Functions.isAppInstalled(this))
            {
                // If the game is installed.
                setContentView(R.layout.score);
                String cScore = Functions.CurrentScore();
                Toast.makeText(this, cScore, Toast.LENGTH_SHORT).show();
            }
            else
            {
                // If game is not installed.
                setContentView(R.layout.app_not_installed);
            }
		}
		else
		{
			setContentView(R.layout.no_root_error);
		}

		// Setting ActionBar based on SDK Version.
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
		{
			getActionBar();
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
            HomePage.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.bounce_interpolator);
            return true;
        }
        else if (id == R.id.rate)
        {
            Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
            rate.setData(Uri.parse("https://play.google.com/store/apps/details?id=app.codelhiites.swingcheats"));
            startActivity(rate);
            HomePage.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            return true;
        }
        else if (id == R.id.more)
        {
            Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
            rate.setData(Uri.parse("https://play.google.com/store/apps/developer?id=ITIL+CIC"));
            startActivity(rate);
            HomePage.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	// Function called on load button click on activity_home_page.
	public void load(View view)
	{
		if (Functions.isAppInstalled(this))
		{
			// If the game is installed.
			setContentView(R.layout.score);
			String cScore = Functions.CurrentScore();
			Toast.makeText(this, cScore, Toast.LENGTH_SHORT).show();
//			TextView currentScore = (TextView)findViewById(R.id.currentScore);
//			currentScore.setText(cScore);
		}
		else
		{
			// If game is not installed.
			setContentView(R.layout.app_not_installed);
		}
	}

	// Function called for installing game.
	public void installApp(View view)
	{
		Functions.installApp(this);
		finish();
	}

	// Function for applying user provided score.
	public void applyNewScore(View view)
	{
		EditText newScore = (EditText)findViewById(R.id.newScore);
		Functions.applyNewScore(newScore);
//		load(view);
		finish();
	}
}
// CLASS ENDS HERE.
