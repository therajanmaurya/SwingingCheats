package app.codelhiites.swingcheats;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created on 8/23/14.
 *
 * Class for helper methods.
 */

public class Functions
{

	// Useless constructor.
	public Functions()
	{
		// Does nothing.
	}

	// Method for checking whether or not the application is installed.
	public static boolean isAppInstalled(Context context)
	{
		boolean installed = false;
		PackageManager app = context.getPackageManager();
		try
		{
			app.getPackageInfo("com.dotgears.swing", PackageManager.GET_ACTIVITIES);
			installed = true;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			installed = false;
		}
		return installed;
	}

	// Method for sending intent if app not installed.
	public static void installApp(Context context)
	{
		try
		{
			context.startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://details?id=com.dotgears.swing")));
		}
		catch (ActivityNotFoundException e)
		{
			context.startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://play.google.com/store/apps/details?id=com.dotgears.swing")));
		}
	}

    //Source code on github
    public static void source_code(Context context){
        try
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + "@string/appname")));
        }
        catch (ActivityNotFoundException e)
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + "@string/appname")));
        }
    }

	// Method for returning current score from XML. !!!ROOT WORK DONE HERE!!!
	public static String CurrentScore()
	{
		try
		{
			// Running commands for copying file into temp directory.
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(su.getOutputStream());
			os.writeBytes("mkdir /data/local/tmp/.swingtmp \n");
			os.writeBytes("busybox cp /data/data/com.dotgears" +
					".swing/shared_prefs/SwingCopters" +
					".xml /data/local/tmp/.swingtmp/ \n");
			os.writeBytes("busybox chmod 777 /data/local/tmp/.swingtmp/SwingCopters.xml \n");
			os.writeBytes("exit\n");
			os.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return parseScoreFromXML("/data/local/tmp/.swingtmp/SwingCopters.xml");
	}

	// Parsing SwingCopters.xml file for scores.
	protected static String parseScoreFromXML(String file)
	{
		String score = "0000";
		try
		{
			BufferedReader xmlFile = new BufferedReader(new FileReader(file));
			String line;
			line = xmlFile.readLine();
			while(line!=null)
			{
				if (line.contains("value"))
				{
					score = line.substring(line.indexOf("value") + 7);
					score = score.substring(0, score.length() - 4);
				}
				line = xmlFile.readLine();
			}
			xmlFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// Method to be implemented for extracting score from XML.
		return score;
	}

	// Method for applying new score. !!!ROOT WORK DONE HERE!!!
	public static void applyNewScore(EditText newScore)
	{

		try
		{
			// Running commands for moving temp file back to data and deleting temp directory.
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(su.getOutputStream());
			os.writeBytes("touch /data/data/com.dotgears.swing/shared_prefs/SwingCopters.xml \n");
			os.writeBytes("STR=\"<?xml version=\'1.0\' encoding=\'utf-8\' standalone=\'yes\' ?>\"" +
					" \n");
			os.writeBytes("echo \"$STR\" > /data/data/com.dotgears" +
					".swing/shared_prefs/SwingCopters.xml \n");
			os.writeBytes("STR=\'<map>\' \n");
			os.writeBytes("echo \"$STR\" >> /data/data/com.dotgears" +
					".swing/shared_prefs/SwingCopters.xml \n");
			os.writeBytes("STR=\'<int name=\"score\" value=\"" + newScore.getText().toString() +
							"\" />\' \n");
			os.writeBytes("echo \"$STR\" >> /data/data/com.dotgears" +
					".swing/shared_prefs/SwingCopters.xml \n");
			os.writeBytes("STR=\'</map>\' \n");
			os.writeBytes("echo \"$STR\" >> /data/data/com.dotgears" +
					".swing/shared_prefs/SwingCopters.xml \n");
			os.writeBytes("exit \n");
			os.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// ***NO USE RIGHT NOW*** - Parsing SwingCopters.xml for updating scores.

/*	protected static void updateScoreXML(String file, String score)
	{
		// Method to be implemented for updating score in XML.
		try
		{
			String currentScore = parseScoreFromXML(file);
			BufferedReader xmlFile = new BufferedReader(new FileReader(file));
			BufferedWriter XMLFile = new BufferedWriter(new FileWriter("/data/local/tmp/.swingtmp/newXML.xml"));
			String line;
			line = xmlFile.readLine();
			while (line!=null)
			{
				if(line.contains("value"))
				{
					line.replace(currentScore, score);
				}
				XMLFile.write(line, 0, line.length());
				line = xmlFile.readLine();
			}
			xmlFile.close();
			XMLFile.close();
			BufferedWriter xmlFile = new BufferedWriter(new FileWriter(file, false));
			xmlFile.write("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
			xmlFile.newLine();
			xmlFile.write("<map>");
			xmlFile.newLine();
			xmlFile.write("<int name=\"score\" value=\"" + score + "\" />");
			xmlFile.newLine();
			xmlFile.write("</map>");
			xmlFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
*/
	// Function for checking whether or not the device is rooted.
	public static boolean isRooted()
	{
		boolean found = false;
		try
		{
			String[] places = { "/sbin/", "/system/bin/", "/system/xbin/",
					"/data/local/xbin/", "/data/local/bin/",
					"/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" };
			for (String where : places)
			{
				if (new File(where + "su").exists())
				{
					try
					{
						Process p = Runtime.getRuntime().exec("su exit \n");
						p.destroy();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					found = true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return found;
	}
}
// CLASS ENDS HERE.
