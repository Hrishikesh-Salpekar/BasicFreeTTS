package com.example.basicfreetts;


import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mapzen.speakerbox.Speakerbox;
import com.sun.speech.freetts.*;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.speech.Central;
import javax.speech.EngineException;
//import android.speech.freetts.TextToSpeech;


//private void speakWords(String speech) {
////implement TTS here
//        Voice v = VoiceManager.getInstance().getVoice("kevin16"); //Get the "kevin16" voice instance
//        v.allocate();       //Allocate the necessary resources
//        v.setPitch(100.0F);      //Set Pitch
//        v.setVolume(3.0F); 		 //Set Volume
//        v.setRate(200.0F); 	 	 //Set Speed
//        String s ="One of the open source TTS systems is “FreeTTS”.\n\n FreeTTS supports adding own lexicon and to train custom voices?    The goal is to customize FreeTTS by adding custom lexicon, custom voice, and changing different voice parameters!\r\n" +
//        " "; //Input string
//        v.speak(s);              //Call the speak function to convert the input string to voice
//}

public class MainActivity extends AppCompatActivity implements OnClickListener,TextToSpeech.OnInitListener {
   public EditText enteredText;
   Voice v;
   TextToSpeech tts;
    private void speakWords() {
//implement TTS here
        //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        v = VoiceManager.getInstance().getVoice("kevin16"); //Get the "kevin16" voice instance
        v.allocate();       //Allocate the necessary resources
        v.setPitch(100.0F);      //Set Pitch
        v.setVolume(3.0F); 		 //Set Volume
        v.setRate(200.0F); 	 	 //Set Speed
        String s ="One of the open source TTS systems is “FreeTTS”.\n\n FreeTTS supports adding own lexicon and to train custom voices?    The goal is to customize FreeTTS by adding custom lexicon, custom voice, and changing different voice parameters!\r\n" +
                " "; //Input string
        v.speak(s);//Call the speak function to convert the input string to voice
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredText = (EditText)findViewById(R.id.speechtext);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Text to Speech");
        setSupportActionBar(myToolbar);


       // VoiceManager vm = VoiceManager.getInstance();
        tts = new TextToSpeech(this, this,"com.sun.speech.engine");

       // Log.e("voices",vm.getVoiceInfo());
        Speech();


        Button speakButton = (Button)findViewById(R.id.speak);
        speakButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String words = enteredText.getText().toString().trim();
        if(words.isEmpty()){
            Toast.makeText(getApplicationContext(),"enter text first",Toast.LENGTH_SHORT).show();
        }
        else{
            tts.speak(words, TextToSpeech.QUEUE_FLUSH, null);
        }
       // speakWords();

    }


    public void Speech()
    {
        // Bypass need for speech.properties file / NullPointer exception
        try
        {
            System.setProperty("logLevel", "OFF"); // INFO or WARN are also valid
            System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            // FixMe: Get Arctic voices working.
            //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_slt_arctic.ArcticVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        }
        catch(EngineException e)
        {
            System.out.println("Unable to provide speech synthesis: " + e);
            System.exit(1);
        }
        v = VoiceManager.getInstance().getVoice("kevin16");
        //voice = VoiceManager.getInstance().getVoice("slt_arctic");
       // MediaPlayer mp = new MediaPlayer();
        //SingleFileAudioPlayer sp = new SingleFileAudioPlayer("a",AudioFile)
        //System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer", "com.sun.speech.freetts.audio.SingleFileAudioPlayer");
        //v.setAudioPlayer(new JavaClipAudioPlayer());


       // Speakerbox speakerbox = new Speakerbox(getApplication());
       // speakerbox.play("Hello i am meer");

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            Set<String> a=new HashSet<>();
            a.add("male");
            float pitch = (float) 0.6;

            int result = tts.setLanguage(Locale.US);
            tts.setEngineByPackageName("com.sun.speech.freetts");

            android.speech.tts.Voice voice = new android.speech.tts.Voice("en-us-x-sfg#male_1-local",new Locale("en","UK"),400,200,true,a);
            tts.setVoice(voice);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {

                tts.speak("hello enter any text to speak", TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}
