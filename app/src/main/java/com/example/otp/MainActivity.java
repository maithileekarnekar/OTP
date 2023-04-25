package com.example.otp;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupOptions;
    private RadioButton radio4, radio6, radio8;
    private LinearLayout Container;
    private EditText[] editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void initViews() {
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        radio4 = findViewById(R.id.radio4);
        radio6 = findViewById(R.id.radio6);
        radio8 = findViewById(R.id.radio8);
        Container = findViewById(R.id.container);

    }
     private void setupListeners() {

        RadioOnCheckedChangeListener radioOnCheckedChangeListener =
                new RadioOnCheckedChangeListener();

        radio4.setOnCheckedChangeListener(new RadioOnCheckedChangeListener());
        radio6.setOnCheckedChangeListener(new RadioOnCheckedChangeListener());
        radio8.setOnCheckedChangeListener(new RadioOnCheckedChangeListener());
     }

     private class RadioOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

             switch (compoundButton.getId()) {
                 case R.id.radio4:
                     if(isChecked) {
                         setEditTexts(4);
                     }
                     break;
                 case R.id.radio6:
                     if(isChecked) {
                         setEditTexts(6);
                     }
                     break;
                 case R.id.radio8:
                     if (isChecked) {
                         setEditTexts(8);
                     }
                     break;
             }
         }
    }

    private void setEditTexts(int count) {

        Container.removeAllViews();
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                );

      editTexts = new EditText[count];
        for(int i = 0; i< count; i++) {
            editTexts[i] = new EditText(MainActivity.this);
            editTexts[i].setLayoutParams(layoutParams);
            editTexts[i].setInputType(InputType.TYPE_CLASS_NUMBER);

            Container.addView(editTexts[i]);
            editTexts[i].addTextChangedListener(new MyTextWatcher(i));
        }
        editTexts[0].requestFocus();


    }

    private class MyTextWatcher implements TextWatcher {
        private int currentIndex;
        public MyTextWatcher(int currentIndex) {
            this.currentIndex = currentIndex;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1) {
                if (currentIndex < editTexts.length - 1) {
                    editTexts[currentIndex + 1].requestFocus();
                } else {
                    mt("Verified");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
