package dev.johnw.android.plex;

import android.app.Activity;
import android.graphics.drawable.Drawable;

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
import org.apache.http.client.ClientProtocolException;
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
        t.setTextColor(0xFF3333CC);
    }
    
    public void showNav(View v){
    	setContentView(R.layout.main);
    }
    
    public void nav(View v) throws IOException{
    	try{
	    	TextView t = (TextView)findViewById(R.id.headerText);
	        t.setText((String) v.getTag());
	        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/navigation/"+(String) v.getTag());
	        feedUrl.openConnection().getInputStream();
    	}
    	catch(Exception e){}
    }
    
    
    public void play(View v) throws IOException{
    	try{
	    	TextView t = (TextView)findViewById(R.id.headerText);
	        t.setText((String) v.getTag());
	        URL feedUrl = new URL("http://192.168.1.105:32400/system/players/John-PC/playback/"+(String) v.getTag());
	        feedUrl.openConnection().getInputStream();
    	}
    	catch(Exception e){}
    }
    	
    
    
    public void viewList(View v) throws IOException, URISyntaxException{
    	setContentView(R.layout.list);
    	//URL feedUrl = new URL("http://192.168.1.105:32400/library/sections/2/all");
    	 //InputStream in = new BufferedInputStream(feedUrl.openConnection().getInputStream());
    	 //readStream(in);
    	 TextView t = (TextView)findViewById(R.id.headerText);
         t.setText("HelloHiHey");
         //String mpath = "http://192.168.1.105:32400/library/sections/2/newest";
         String mpath = "http://192.168.1.105:32400/library/sections/";
         LinearLayout ll = (LinearLayout)findViewById(R.id.linearList);
         drawList(ll, mpath);
         
    	 
        
    }
    
    public String quickParse(String parseString, String prop){
    	String[] elements = parseString.split(prop+"=\"");
    	String value = "";
    	if (elements.length > 1){
    		value = elements[1].split("\"")[0];
    	
    	}
    return value;	
    }

    public void drawList(LinearLayout ll, String mpath) throws URISyntaxException, ClientProtocolException, IOException{
    	ll.removeAllViews();
    	
    	InputStream is = null;
        HttpClient httpclient = new DefaultHttpClient();

        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
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
            
            
            
            String[] dirs = page.split("<Directory");
            if (dirs.length > 1){
              	 for (int di = 1; di < dirs.length; di++){
          		 
          		 String path = "";
          		 
              	 TextView tv = new TextView(this);
              	 tv.setText(quickParse(dirs[di], "title") );
              	 //tv.setTag(quickParse(videos[vi], "key"));
              	 String keyS = quickParse(dirs[di], "key");
              	 String args = "";
              	 if (keyS.substring(0,1).equals("/") ){
              		 args = "http://192.168.1.105:32400"+keyS+"/";
              	 }
              	 else {
              		args = mpath+quickParse(dirs[di], "key")+"/"; 
              	 }
              	 
              	 tv.setTag(args );
              	 tv.setTextSize((float) 20);
              	 tv.setTextColor(0xFF3333CC);
              	 tv.setBackgroundColor(getResources().getColor(R.drawable.background_color));
              	 //tv.setTextAppearance(getApplicationContext(), "?android:attr/textAppearanceLarge")
              	 //tv.setBackgroundDrawable(d)
   				
   				tv.setOnClickListener(new OnClickListener() {

   					public void onClick(View v) {
   						TextView t = (TextView)findViewById(R.id.headerText);
   						LinearLayout ll = (LinearLayout)findViewById(R.id.linearList);
   						String mpath =(String) v.getTag();
   						t.setText(mpath);
   						try {
							drawList(ll, mpath);
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
   					}});
              	 
              	 ll.addView(tv);
               }
            }
            
            
            
            
            
            String[] videos = page.split("<Video");
            if (videos.length > 1){
           	 for (int vi = 1; vi < videos.length; vi++){
       		 
       		 String path = "";
       		String watchString = ""; 
           	 TextView tv = new TextView(this);
           	 try{
           		 	Float offset = new Float(quickParse(videos[vi], "viewOffset"));
           		 	Float duration = new Float(quickParse(videos[vi], "duration"));
           		 	Float watched = (float) Math.ceil(offset/duration*100);
           		 	watchString = " -" + watched.toString()+"%";
           	 }
           	 catch(Exception e){
           		watchString = ""; 
           	 }
           	 tv.setText(quickParse(videos[vi], "grandparentTitle")+" - "+quickParse(videos[vi], "title")+watchString);
           	 //tv.setTag(quickParse(videos[vi], "key"));
           	 String args = "?path="+mpath+"&key="+quickParse(videos[vi], "key")+"&viewOffset="+quickParse(videos[vi], "viewOffset");
           	 tv.setTag(args );
          	 tv.setTextSize((float) 20);
          	 tv.setTextColor(0xFFFC7B12);
          	 Float viewCount = (float) 0;
          	 try{
          		 viewCount = new Float(quickParse(videos[vi], "viewCount"));
          	 }
          	 catch(Exception e){
          		 
          	 }
          	 if ( viewCount >= 1){
          		tv.setTextColor(0xFF2E2E2E);
          	 }
          	 //tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_color));
           	 
           	 //tv.setTextAppearance(getApplicationContext(), "?android:attr/textAppearanceLarge")
           	
				
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
           	 //t.setText(quickParse(videos[vi], "grandparentTitle")+" - "+quickParse(videos[vi], "title") );
           	 ll.addView(tv);
            }
           	 


           	 //t.setText(quickParse(videos[1], "grandparentTitle")+" - "+quickParse(videos[1], "title") );
            }
            
            
            
            
            } catch (Exception e){
            	e.printStackTrace();
            }
        		finally {
            if (in != null) {
                try {
                    in.close();
                    } catch (IOException e) {
                }
            }
        }
    }
    
    

}