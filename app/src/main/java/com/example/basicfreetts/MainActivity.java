package com.example.basicfreetts;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import com.sun.speech.freetts.*;
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

public class MainActivity extends AppCompatActivity implements OnClickListener {
    EditText enteredText;
    private void speakWords() {
//implement TTS here
        Voice v = VoiceManager.getInstance().getVoice("kevin16"); //Get the "kevin16" voice instance
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
        EditText enteredText = (EditText)findViewById(R.id.Text);


        Button speakButton = (Button)findViewById(R.id.speak);
        speakButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       // String words = enteredText.getText().toString();
        speakWords();
    }
}
