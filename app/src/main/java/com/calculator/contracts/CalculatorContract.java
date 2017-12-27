package com.calculator.contracts;

/**
 * Created by nndra on 13-Dec-17.
 */

public interface CalculatorContract {


    //Evaluates expression.
    interface DisplayData{
            void showResults(String results);
            void showMessages(String message);
    }

    //pass data from number pad and expression display to presenter.
    interface PassDataToPresenter{
        void onClick();
        void onLongClick();
            void numpadClick(int number);
            void decimalClick();
            void evaluateClick();
            void operationClick(String operator);
    }


}
