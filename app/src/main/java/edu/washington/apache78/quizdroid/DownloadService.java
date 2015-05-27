package edu.washington.apache78.quizdroid;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by apache78 on 5/24/2015.
 */
public class DownloadService extends IntentService {
    public DownloadService(){super("DownloadService");}

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("URL");

        BufferedReader input=null;
        PrintWriter output=null;
        if(url.equals("")){
            Log.e("DOWNLOAD", "no url");
            return;
        }

        try{
            URL download = new URL(url);
            input = new BufferedReader(new InputStreamReader(download.openStream()));
            output = new PrintWriter(new FileOutputStream(this.getFilesDir()+ "/questions.json"));
            String line;
            while((line=input.readLine()) !=null){
                output.write(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(input!=null){
                    input.close();
                }
                if(output !=null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
