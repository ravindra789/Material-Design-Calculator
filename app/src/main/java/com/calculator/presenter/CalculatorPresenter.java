package com.calculator.presenter;

import com.calculator.contracts.CalculatorContract;
import com.calculator.model.CalculatorCalculations;
import com.calculator.model.CalculatorResult;

/**
 * Created by nndra on 15-Dec-17.
 */

public class CalculatorPresenter implements CalculatorContract.PassDataToPresenter, CalculatorResult {

    private CalculatorContract.DisplayData mDisplayData;
    private CalculatorCalculations calculatorCalculations;

    public CalculatorPresenter(CalculatorContract.DisplayData displayData) {
        mDisplayData = displayData;
        calculatorCalculations = new CalculatorCalculations();
        calculatorCalculations.setCalculationResult(this);
    }

    @Override
    public void onClick() {
        calculatorCalculations.clickClearText();
    }

    @Override
    public void onLongClick() {
        calculatorCalculations.longClickClearText();
    }

    @Override
    public void numpadClick(int number) {
        calculatorCalculations.addNumber(""+number);
    }

    @Override
    public void decimalClick() {
        calculatorCalculations.addDecimal();
    }

    @Override
    public void evaluateClick() {
        calculatorCalculations.CalculateExpression();
    }

    @Override
    public void operationClick(String operator) {
        calculatorCalculations.addOperator(operator);
    }


    @Override
    public void onExpressionChange(String result, boolean status) {
        if(status){
            mDisplayData.showResults(result);
        }else{
            mDisplayData.showMessages(result);
        }
    }
}
