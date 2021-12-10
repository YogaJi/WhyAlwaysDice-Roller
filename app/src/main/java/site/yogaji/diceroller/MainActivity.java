package site.yogaji.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
  implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private int currentDice;
    private String addDiceTextString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //set preference
//        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
//        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //get button id
        Button rollOnceButton = findViewById(R.id.rollOnceButton);
        Button rollTwiceButton = findViewById(R.id.rollTwiceButton);

        Button addRoll = findViewById(R.id.addRoll);

        //get rolling dices id
//        TextView roll1Text = findViewById(R.id.roll1Text);
//        TextView roll2Text = findViewById(R.id.roll2Text);

        //set listener
        rollOnceButton.setOnClickListener(this);
        rollTwiceButton.setOnClickListener(this);
        addRoll.setOnClickListener(this);

        //get user add dice id
        EditText addDiceEdit = findViewById(R.id.addDiceEdit);

        //set spinner
        Spinner diceSpinner = findViewById(R.id.diceSpinner);
        //add dice list for dice spinner
        List<String> diceList = new ArrayList<>();
        diceList.add("4");
        diceList.add("6");
        diceList.add("8");
        diceList.add("10");
        diceList.add("12");
        diceList.add("20");
        diceList.add("10s");

        //add Adapter for diceSpinner
        ArrayAdapter diceSpinnerAdapter = new ArrayAdapter<>(this, R.layout.custom_spinner_text_item, diceList);
        diceSpinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);


        diceSpinner.setAdapter(diceSpinnerAdapter);
        //set spinner Listener
        diceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(Objects.equals(diceList.get(position), "10s")){
                    currentDice = 0;
                }else{
                    currentDice = Integer.parseInt(diceList.get(position));
                }

                Toast.makeText(MainActivity.this, "You choose " + diceList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        //get user add dice
        addDiceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                addDiceTextString = String.valueOf(addDiceEdit.getText());
//                addDiceText = Integer.parseInt(addDiceTextString);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //add dice listener

        addRoll.setOnClickListener(v -> {
            if(Objects.equals(addDiceTextString, "0")){
                addDiceEdit.setText("");
                Toast.makeText(MainActivity.this, "invalid" , Toast.LENGTH_SHORT).show();
                //
            }else{
                diceList.add(addDiceTextString);
                addDiceEdit.setText("");

            }

        });


    }//end of onCreate


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        TextView  roll1Text = findViewById(R.id.roll1Text);
        TextView  roll2Text = findViewById(R.id.roll2Text);

        int currentNum;
        switch (view.getId()) {
            case R.id.rollOnceButton:
//                rollOnce
                if(currentDice == 0){
                    int rand10s = 10;
                    currentNum = (int) (Math.random()*rand10s+1)*10;
                }else{
                    currentNum = (int) (Math.random()*currentDice+1);
                }
                roll1Text.setText(String.valueOf(currentNum));

                roll2Text.setText("-");
                break;
            case R.id.rollTwiceButton:
//                rollTwice
                if(currentDice == 0){
                    int rand10s = 10;

                    currentNum = (int) (Math.random()*rand10s+1)*10;
                    roll1Text.setText(String.valueOf(currentNum));
                    currentNum = (int) (Math.random()*rand10s+1)*10;
                }else{
                    currentNum = (int) (Math.random()*currentDice+1);
                    roll1Text.setText(String.valueOf(currentNum));
                    currentNum = (int) (Math.random()*currentDice+1);
                }
                roll2Text.setText(String.valueOf(currentNum));

                break;

            default:
                Log.e("click event", "wrong button");

        }

    }//end of onClick

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


}