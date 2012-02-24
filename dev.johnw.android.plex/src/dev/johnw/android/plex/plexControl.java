package dev.johnw.android.plex;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient; 

public class plexControl extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("HelloHiHey");
    }
    public void showNav(View v){
    	setContentView(R.layout.main);
    }
    public void navPlay(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/playback/play");
        feedUrl.openConnection().getInputStream();
    }

    public void navRew(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/playback/stepBack");
        feedUrl.openConnection().getInputStream();
    }
    public void navFF(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/playback/stepForward");
        feedUrl.openConnection().getInputStream();
    }
    public void navUp(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/moveUp");
        feedUrl.openConnection().getInputStream();
    }
    public void navDown(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/moveDown");
        feedUrl.openConnection().getInputStream();
    }
    public void navLeft(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/moveLeft");
        feedUrl.openConnection().getInputStream();
    }
    public void navRight(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/moveRight");
        feedUrl.openConnection().getInputStream();
    }
    public void navOk(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/select");
        feedUrl.openConnection().getInputStream();
    }
    public void navBack(View v) throws IOException{
    	TextView t = (TextView)findViewById(R.id.headerText);
        t.setText("Whore");
        //WebView webview = new WebView(this);
        //webview.loadUrl("http://192.168.1.105/Plex.php?path=/system/players/John-PC/playback/play&oldpath=/library/sections/2/newest");
        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/back");
        feedUrl.openConnection().getInputStream();
    }
    public void viewList(View v) throws IOException, URISyntaxException{
    	setContentView(R.layout.list);
    	//URL feedUrl = new URL("http://192.168.1.105:32400/library/sections/2/all");
    	 //InputStream in = new BufferedInputStream(feedUrl.openConnection().getInputStream());
    	 //readStream(in);
    	 TextView t = (TextView)findViewById(R.id.headerText);
         t.setText("HelloHiHey");
         InputStream is = null;
         HttpClient httpclient = new DefaultHttpClient();

         BufferedReader in = null;
         try {
             HttpClient client = new DefaultHttpClient();
             HttpGet request = new HttpGet();
             String mpath = "http://192.168.1.105:32400/library/sections/2/newest";
             request.setURI(new URI(mpath));
             HttpResponse response = client.execute(request);
             in = new BufferedReader
             (new InputStreamReader(response.getEntity().getContent()));
             StringBuffer sb = new StringBuffer("");
             String line = "";
             String NL = System.getProperty("line.separator");
             while ((line = in.readLine()) != null) {
                 sb.append(line + NL);
             }
             in.close();
             String page = sb.toString();
             String title = "";
             String GPtitle = "";
             String key="";
             String meta = "";
             //t.setText(page);
             String[] videos = page.split("<Video");
             LinearLayout ll = (LinearLayout)findViewById(R.id.linearList);
             if (videos.length > 1){
            	 for (int vi = 1; vi < videos.length; vi++){
        		 
        		 String path = "";

            	 TextView tv = new TextView(this);
            	 tv.setText(quickParse(videos[vi], "grandparentTitle")+" - "+quickParse(videos[vi], "title") );
            	 //tv.setTag(quickParse(videos[vi], "key"));
            	 String args = "?path="+mpath+"&key="+quickParse(videos[vi], "key");
            	 tv.setTag(args );
            	
				
				tv.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						TextView t = (TextView)findViewById(R.id.headerText);
						String url = "http://192.168.1.105:32400/system/players/John-PC/application/playMedia"+ (String) v.getTag();
						//t.setText(url);
						URL feedUrl;
						try {
							feedUrl = new URL(url);
							feedUrl.openConnection().getInputStream();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
					}});
            	 t.setText(quickParse(videos[vi], "grandparentTitle")+" - "+quickParse(videos[vi], "title") );
            	 ll.addView(tv);
             }
            	 


            	 //t.setText(quickParse(videos[1], "grandparentTitle")+" - "+quickParse(videos[1], "title") );
             }
             
             
             
             
             } finally {
             if (in != null) {
                 try {
                     in.close();
                     } catch (IOException e) {
                 }
             }
         }
         
    	 
        
    }
    
    public String quickParse(String parseString, String prop){
    	String[] elements = parseString.split(prop+"=\"");
    	String value = "";
    	if (elements.length > 1){
    		value = elements[1].split("\"")[0];
    	
    	}
    return value;	
    }

    
    

}