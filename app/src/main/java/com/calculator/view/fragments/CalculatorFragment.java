package com.calculator.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.calculator.R;
import com.calculator.contracts.CalculatorContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements CalculatorContract.DisplayData {

    CalculatorContract.PassDataToPresenter mPassDataToPresenter;

    @BindView(R.id.txt_display_numbers)
    TextView displayText;

    public static CalculatorFragment newInstance(){
        return new CalculatorFragment();
    }

    public CalculatorFragment() {
        // Required empty public constructor
    }

    public void setPresenter(CalculatorContract.PassDataToPresenter passDataToPresenter){
        mPassDataToPresenter = passDataToPresenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @OnClick(R.id.img_btn_display_delete)
    public void clearTextOnClick(View v){
        mPassDataToPresenter.onClick();
    }

    @OnLongClick(R.id.img_btn_display_delete)
    public boolean clearTextOnLongClick(View v){
        mPassDataToPresenter.onLongClick();
        return true;
    }

    @OnClick({R.id.btn_number_one,  R.id.btn_number_two, R.id.btn_number_three, R.id.btn_number_four,
            R.id.btn_number_five, R.id.btn_number_six, R.id.btn_number_seven, R.id.btn_number_eight,
            R.id.btn_number_nine, R.id.btn_number_zero})
    public void onNumbersClicked(Button button){
        mPassDataToPresenter.numpadClick(Integer.parseInt(button.getText().toString()));
    }

    @OnClick({R.id.btn_operator_add,   R.id.btn_operator_divide,
            R.id.btn_operator_minus, R.id.btn_operator_multiply})
    public void onOperatorsClicked(Button button){
        mPassDataToPresenter.operationClick(button.getText().toString());
    }

    @OnClick(R.id.btn_operator_dot)
    public void onDecimalPointClicked(Button button){
        mPassDataToPresenter.decimalClick();
    }

    @OnClick(R.id.btn_operator_equal)
    public void onEvaluateClicked(Button button){
        mPassDataToPresenter.evaluateClick();
    }

    @Override
    public void showResults(String results) {
        System.out.println("Cal - "+results);
        displayText.setText(""+results);
    }

    @Override
    public void showMessages(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }
}
