package com.calculator.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.calculator.R;
import com.calculator.presenter.CalculatorPresenter;
import com.calculator.view.fragments.CalculatorFragment;



public class MainCalculatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalculatorFragment calculatorFragment = (CalculatorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.root_fragment_calculator_display);

        CalculatorPresenter calculatorPresenter = new CalculatorPresenter(calculatorFragment);
        calculatorFragment.setPresenter(calculatorPresenter);


    }
}
