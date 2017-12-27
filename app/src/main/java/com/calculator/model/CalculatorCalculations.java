package com.calculator.model;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.regex.PatternSyntaxException;

/**
 * Created by nndra on 15-Dec-17.
 */

public class CalculatorCalculations {

        private static String mCurrentExpression;
        private CalculatorResult mCalculatorResult;
        private BigDecimal result = null;

    public CalculatorCalculations() {

    }

    public void setCalculationResult(CalculatorResult calculationResult){
            mCalculatorResult = calculationResult;
            mCurrentExpression = "";
        }

    /**
     * clear single char.
     */
    public void clickClearText(){

        if(mCurrentExpression.length() > 0){
            mCurrentExpression = mCurrentExpression.substring(0,mCurrentExpression.length()-1);
            mCalculatorResult.onExpressionChange(mCurrentExpression, true);
        }else{
            mCalculatorResult.onExpressionChange("Invalid input", false);
        }
    }

    /**
     * clear whole expression.
     */
    public void longClickClearText(){
        if(!mCurrentExpression.equalsIgnoreCase("")){
            mCurrentExpression = "";
            mCalculatorResult.onExpressionChange(mCurrentExpression, true);
        }else{
            mCalculatorResult.onExpressionChange("Invalid input", false);
        }
    }

    /**
     * append a new number to expression.
     * invalid if starts with or contains 0.
     * limit 20char
     */
    public void addNumber(String number){
        if(mCurrentExpression.startsWith("0") && number.equalsIgnoreCase("0")){
            mCalculatorResult.onExpressionChange("Invalid input", false);
        }else {
            if (mCurrentExpression.length() <= 20) {
                mCurrentExpression = mCurrentExpression + number;
                mCalculatorResult.onExpressionChange(mCurrentExpression, true);
            }else{
                mCalculatorResult.onExpressionChange("Expression too long.", false);
            }
        }
    }

    /**
     * append decimal point to expression.
     */
    public void addDecimal(){
        if(invalidateExpression(mCurrentExpression)){
            mCurrentExpression+= ".";
            mCalculatorResult.onExpressionChange(mCurrentExpression, true);
        }
    }

    /**
     * add operator to the expression.
     * "/"
     * "*"
     * "-"
     * "+"
     */
    public void addOperator(String operator){
        if(invalidateExpression(mCurrentExpression)){
            mCurrentExpression+= operator;
            mCalculatorResult.onExpressionChange(mCurrentExpression, true);
        }
    }

    /**
     * evaluate the expression.
     */
    public boolean invalidateExpression(String expression){

        if(expression.equalsIgnoreCase("/") || expression.equalsIgnoreCase("*") ||
                expression.equalsIgnoreCase("+")  || expression.equalsIgnoreCase("-") ){
            mCalculatorResult.onExpressionChange("Invalid Expression.", false);
        }else if(expression.equalsIgnoreCase("") || expression.length() > 20) {
            mCalculatorResult.onExpressionChange("Invalid Expression.", false);
        }else{
            return true;
        }
        return false;

    }

    public void CalculateExpression(){
        if(invalidateExpression(mCurrentExpression)) {
            try {
                Expression expressionEvaluated = new Expression(mCurrentExpression);
                result = expressionEvaluated.eval();
                mCurrentExpression = ""+result;
            }catch (NumberFormatException e){
                mCalculatorResult.onExpressionChange("Invalid Expression.", false);
            }
            mCalculatorResult.onExpressionChange(mCurrentExpression, true);
        }else{
            mCalculatorResult.onExpressionChange("Invalid Expression.", false);
        }
    }

}
