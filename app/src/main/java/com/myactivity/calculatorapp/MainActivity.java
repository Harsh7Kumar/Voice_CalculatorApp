package com.myactivity.calculatorapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.Locale;

import static android.Manifest.permission.RECORD_AUDIO;


public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    ImageView btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    ImageView btn_dot, btn_equal, btn_ac, btn_module, btn_plus, btn_minus, btn_multiplication, btn_divide, btn_delete, btnSpeak;
    private TextView inputTxt, outPuttxt;
    String data;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecognizer;
    protected static final int RESULT_SPEECH = 0;
    private final int REQ_CODE_SPEECH_INPUT = 1;
    private boolean lastDot;
    private boolean lastNumeric;
    private boolean stateError;

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        setContentView(R.layout.activity_main);
        outPuttxt = findViewById(R.id.outPuttxt);
        inputTxt = findViewById(R.id.inputTxt);

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);

        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        btn_ac = findViewById(R.id.btn_ac);
        btn_module = findViewById(R.id.btn_module);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiplication = findViewById(R.id.btn_multiplication);
        btn_divide = findViewById(R.id.btn_divide);
        btn_delete = findViewById(R.id.btn_delete);
        btnSpeak = findViewById(R.id.btnSpeak);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "2");
            }
        });


        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "9");
            }
        });

        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt.setText("");
                outPuttxt.setText("");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + ".");
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "+");
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "-");
            }
        });

        btn_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "%");
            }
        });

        btn_multiplication.setOnClickListener(v -> {
            data = inputTxt.getText().toString();
            inputTxt.setText(data + "×");
        });
        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = inputTxt.getText().toString();
                inputTxt.setText(data + "/");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        intentRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String change = matches.get(0);

                change = change.replace("x", "*");
                change = change.replace("X", "*");
                change = change.replace("add", "+");
                change = change.replace("sub", "-");
                change = change.replace("to", "2");
                change = change.replace(" plus ", "+");
                change = change.replace(" minus ", "-");
                change = change.replace(" times ", "*");
                change = change.replace(" into ", "*");
                change = change.replace(" in2 ", "*");
                change = change.replace(" multiply by ", "*");
                change = change.replace(" divide by ", "/");
                change = change.replace("divide", "/");
                change = change.replace("equal", "=");
                change = change.replace("is equal", "=");
                change = change.replace("equal to", "=");
                change = change.replace("is equal to", "=");
                change = change.replace("equals", "=");
                change = change.replace("equals to", "=");
                change = change.replace("into","*");

                inputTxt.setText(change);
                if(change.contains("=")){
                  //  change=change.replace("=","");
                   // inputTxt.setText(change);
                  //  onEqual();
                        float p = (float) Math.PI;
                        data = inputTxt.getText().toString();

                        data = data.replaceAll("×", "*");
                        data = data.replaceAll("X", "*");
                        data = data.replaceAll("PI", String.valueOf(p));
                        data = data.replaceAll("Pi", String.valueOf(p));
                        data = data.replaceAll("pi", String.valueOf(p));
                        data = data.replaceAll("Star", "*");
                        data = data.replaceAll("into", "*");
                        data = data.replaceAll("multiply", "*");
                        data = data.replaceAll("Multiply", "*");
                        data = data.replaceAll("multiply by", "*");
                        data = data.replaceAll("divide by", "/");
                        data = data.replaceAll("divide", "/");
                        data = data.replaceAll("%", "/100");
                        data = data.replaceAll("equals", "=");
                        data = data.replaceAll("equal", "=");
                        data = data.replaceAll("Equals", "=");
                        data = data.replaceAll("Equal", "=");
                        data = data.replaceAll("÷", "/");

                        Context rhino = Context.enter();
                        rhino.setOptimizationLevel(-1);

                        String finalResult = "";

                        Scriptable scriptable = rhino.initStandardObjects();
                        finalResult = rhino.evaluateString(scriptable, data, "Javsscript", 1, null).toString();

                        outPuttxt.setText(finalResult);

                    }
                else{
                    inputTxt.setText(change);
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float p = (float) Math.PI;
                data = inputTxt.getText().toString();

                data = data.replaceAll("×", "*");
                data = data.replaceAll("X", "*");
                data = data.replaceAll("PI", String.valueOf(p));
                data = data.replaceAll("Pi", String.valueOf(p));
                data = data.replaceAll("pi", String.valueOf(p));
                data = data.replaceAll("Star", "*");
                data = data.replaceAll("into", "*");
                data = data.replaceAll("multiply", "*");
                data = data.replaceAll("Multiply", "*");
                data = data.replaceAll("multiply by", "*");
                data = data.replaceAll("divide by", "/");
                data = data.replaceAll("divide", "/");
                data = data.replaceAll("%", "/100");
                data = data.replaceAll("equals", "=");
                data = data.replaceAll("equal", "=");
                data = data.replaceAll("Equals", "=");
                data = data.replaceAll("Equal", "=");
                data = data.replaceAll("÷", "/");

                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                Scriptable scriptable = rhino.initStandardObjects();
               finalResult = rhino.evaluateString(scriptable, data, "Javsscript", 1, null).toString();

                outPuttxt.setText(finalResult);

            }
        });
        outPuttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.ENGLISH);
                            tts.setSpeechRate(1.0f);
                            tts.speak(outPuttxt.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                        }
                    }
                });
            }
        });
    }

    public void StartButton(View view) {
        speechRecognizer.startListening(intentRecognizer);
    }


    public void backspaceButton(View view) {
        String word = inputTxt.getText().toString();
        int input = word.length();
        if (input > 0) {
            inputTxt.setText(word.substring(0, input - 1));
        }
    }
    private void onEqual() {
        float p = (float) Math.PI;
        data = inputTxt.getText().toString();

        data = data.replaceAll("×", "*");
        data = data.replaceAll("X", "*");
        data = data.replaceAll("PI", String.valueOf(p));
        data = data.replaceAll("Pi", String.valueOf(p));
        data = data.replaceAll("pi", String.valueOf(p));
        data = data.replaceAll("Star", "*");
        data = data.replaceAll("into", "*");
        data = data.replaceAll("multiply", "*");
        data = data.replaceAll("Multiply", "*");
        data = data.replaceAll("multiply by", "*");
        data = data.replaceAll("divide by", "/");
        data = data.replaceAll("divide", "/");
        data = data.replaceAll("%", "/100");
        data = data.replaceAll("equals", "=");
        data = data.replaceAll("equal", "=");
        data = data.replaceAll("Equals", "=");
        data = data.replaceAll("Equal", "=");
        data = data.replaceAll("÷", "/");

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);

        String finalResult = "";

        Scriptable scriptable = rhino.initStandardObjects();
        finalResult = rhino.evaluateString(scriptable, data, "Javsscript", 1, null).toString();

        outPuttxt.setText(finalResult);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode) {
            case KeyEvent.KEYCODE_VOLUME_UP: {
                if (KeyEvent.ACTION_UP == action) {

                    float p = (float) Math.PI;
                    data = inputTxt.getText().toString();

                    data = data.replaceAll("×", "*");
                    data = data.replaceAll("X", "*");
                    data = data.replaceAll("PI", String.valueOf(p));
                    data = data.replaceAll("Pi", String.valueOf(p));
                    data = data.replaceAll("pi", String.valueOf(p));
                    data = data.replaceAll("Star", "*");
                    data = data.replaceAll("into", "*");
                    data = data.replaceAll("multiply", "*");
                    data = data.replaceAll("Multiply", "*");
                    data = data.replaceAll("multiply by", "*");
                    data = data.replaceAll("divide by", "/");
                    data = data.replaceAll("divide", "/");
                    data = data.replaceAll("%", "/100");
                    data = data.replaceAll("equals", "=");
                    data = data.replaceAll("equal", "=");
                    data = data.replaceAll("Equals", "=");
                    data = data.replaceAll("Equal", "=");
                    data = data.replaceAll("÷", "/");

                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);

                    String finalResult = "";

                    Scriptable scriptable = rhino.initStandardObjects();
                    finalResult = rhino.evaluateString(scriptable, data, "Javsscript", 1, null).toString();

                    outPuttxt.setText(finalResult);

                    tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            if (i == TextToSpeech.SUCCESS) {
                                tts.setLanguage(Locale.ENGLISH);
                                tts.setSpeechRate(1.0f);
                                tts.speak(outPuttxt.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                }
                break;
            }

                case KeyEvent.KEYCODE_VOLUME_DOWN: {


                    ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);


                    intentRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intentRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
                    speechRecognizer.setRecognitionListener(new RecognitionListener() {
                        @Override
                        public void onReadyForSpeech(Bundle bundle) {

                        }

                        @Override
                        public void onBeginningOfSpeech() {

                        }

                        @Override
                        public void onRmsChanged(float v) {

                        }

                        @Override
                        public void onBufferReceived(byte[] bytes) {

                        }

                        @Override
                        public void onEndOfSpeech() {

                        }

                        @Override
                        public void onError(int i) {

                        }

                        @Override
                        public void onResults(Bundle bundle) {
                            ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                            String string = "";
                            if (matches != null) {
                                string = matches.get(0);
                                inputTxt.setText(string);
                            }

                        }

                        @Override
                        public void onPartialResults(Bundle bundle) {

                        }

                        @Override
                        public void onEvent(int i, Bundle bundle) {

                        }
                    });
                    if (KeyEvent.ACTION_DOWN == action) {
                        speechRecognizer.startListening(intentRecognizer);
                    }
                }
                break;
            }
        return super.dispatchKeyEvent(event);
    }
}

