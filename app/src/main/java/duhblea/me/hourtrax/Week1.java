package duhblea.me.hourtrax;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Week1 extends Fragment implements View.OnKeyListener {
    private static MainActivity mActivity;
    private EditText monday, tuesday, wednesday, thursday, friday, saturday,
                sunday;
    private TextView weekTotal;
    private Button clearButton;
    private View view;
    private Biweek biweek;


    public static Week1 newInstance(MainActivity aContext) {
        mActivity = aContext;
        Week1 fragment = new Week1();
        return fragment;
    }

    public Week1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_week1, container, false);
        biweek = mActivity.getBiweek();

        initializeUI();
        populateFields();

        return view;
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (!monday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.MONDAY, Float.parseFloat(monday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.MONDAY, 0);
        }

        if (!tuesday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.TUESDAY, Float.parseFloat(tuesday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.TUESDAY, 0);
        }

        if (!wednesday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.WEDNESDAY, Float.parseFloat(wednesday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.WEDNESDAY, 0);
        }

        if (!thursday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.THURSDAY, Float.parseFloat(thursday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.THURSDAY, 0);
        }

        if (!friday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.FRIDAY, Float.parseFloat(friday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.FRIDAY, 0);
        }

        if (!saturday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.SATURDAY, Float.parseFloat(saturday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.SATURDAY, 0);
        }

        if (!sunday.getText().toString().equals("")) {
            biweek.setHourValue(1, Biweek.SUNDAY, Float.parseFloat(saturday.getText().toString()));
        }
        else {
            biweek.setHourValue(1, Biweek.SUNDAY, 0);
        }

        weekTotal.setText("Hours worked:  " + Float.toString(biweek.calculateWeek1()));

        mActivity.updateNavHeader();

        return false;
    }

    private void initializeUI() {
        monday = (EditText) view.findViewById(R.id.monField);
        tuesday = (EditText) view.findViewById(R.id.tueField);
        wednesday = (EditText) view.findViewById(R.id.wedField);
        thursday = (EditText) view.findViewById(R.id.thurField);
        friday = (EditText) view.findViewById(R.id.friField);
        saturday = (EditText) view.findViewById(R.id.satField);
        sunday = (EditText) view.findViewById(R.id.sunField);

        monday.setOnKeyListener(this);
        tuesday.setOnKeyListener(this);
        wednesday.setOnKeyListener(this);
        thursday.setOnKeyListener(this);
        friday.setOnKeyListener(this);
        saturday.setOnKeyListener(this);
        sunday.setOnKeyListener(this);

        weekTotal = (TextView) view.findViewById(R.id.weekTotal);
        clearButton = (Button) view.findViewById(R.id.clearBtn);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monday.setText("");
                tuesday.setText("");
                wednesday.setText("");
                thursday.setText("");
                friday.setText("");
                saturday.setText("");
                sunday.setText("");

                biweek.setHourValue(1, Biweek.MONDAY, 0);
                biweek.setHourValue(1, Biweek.TUESDAY, 0);
                biweek.setHourValue(1, Biweek.WEDNESDAY, 0);
                biweek.setHourValue(1, Biweek.THURSDAY, 0);
                biweek.setHourValue(1, Biweek.FRIDAY, 0);
                biweek.setHourValue(1, Biweek.SATURDAY, 0);
                biweek.setHourValue(1, Biweek.SUNDAY, 0);

                weekTotal.setText("");
            }
        });
    }

    private void populateFields() {
        monday.setText(biweek.getHours(1, Biweek.MONDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.MONDAY)) : "");
        tuesday.setText(biweek.getHours(1, Biweek.TUESDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.TUESDAY)) : "");
        wednesday.setText(biweek.getHours(1, Biweek.WEDNESDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.WEDNESDAY)) : "");
        thursday.setText(biweek.getHours(1, Biweek.THURSDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.THURSDAY)) : "");
        friday.setText(biweek.getHours(1, Biweek.FRIDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.FRIDAY)) : "");
        saturday.setText(biweek.getHours(1, Biweek.SATURDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.SATURDAY)) : "");
        sunday.setText(biweek.getHours(1, Biweek.SUNDAY) != 0.0
                ? Float.toString(biweek.getHours(1, biweek.SUNDAY)) : "");

        weekTotal.setText("Hours worked:  " + Float.toString(biweek.calculateWeek1()));
    }


    @Override
    public void onPause() {
        super.onPause();

        biweek.saveWeek1();
    }
}
