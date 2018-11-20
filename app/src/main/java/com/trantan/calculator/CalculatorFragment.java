package com.trantan.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private static final String SHARED_PREFERENCES_NAME = "result";
    private static final String SHARED_PREFERENCES_KEY = "last_result";
    private static final String OPERATOR_DIV = "÷";
    private static final String OPERATOR_MUL = "×";
    private static final String OPERATOR_PULS = "+";
    private static final String OPERATOR_MINUS = "−";
    private static final String OPERATOR_EQUAL = "=";
    private static final String NUMBER_ONE = "1";
    private static final String NUMBER_TWO = "2";
    private static final String NUMBER_THREE = "3";
    private static final String NUMBER_FOUR = "4";
    private static final String NUMBER_FIVE = "5";
    private static final String NUMBER_SIX = "6";
    private static final String NUMBER_SEVEN = "7";
    private static final String NUMBER_EIGHT = "8";
    private static final String NUMBER_NICE = "9";
    private static final String NUMBER_ZERO = "0";
    private static final String NUMBER_DECIMAL = ".";
    private TextView mTextResult;
    private TextView mTextReset;
    private TextView mTextPulsMinus;
    private TextView mTextPercent;
    private TextView mTextDivide;
    private TextView mTextMultiply;
    private TextView mTextMinus;
    private TextView mTextPlus;
    private TextView mTextEquals;
    private TextView mTextDecimal;
    private TextView mTextNine;
    private TextView mTextEight;
    private TextView mTextSeven;
    private TextView mTextSix;
    private TextView mTextFive;
    private TextView mTextFour;
    private TextView mTextThree;
    private TextView mTextTwo;
    private TextView mTextOne;
    private TextView mTextZero;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        setUpUi(view);
        setEvent();
        getLastResult();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_ac:
                mTextResult.setText(getString(R.string.text_zero));
                break;
            case R.id.text_plus_and_minus:
                oppositeNumber();
                break;
            case R.id.text_percent:
                percentNumber();
                break;
            case R.id.text_divide:
                appendOperator(OPERATOR_DIV);
                break;
            case R.id.text_multiply:
                appendOperator(OPERATOR_MUL);
                break;
            case R.id.text_minus:
                appendOperator(OPERATOR_MINUS);
                break;
            case R.id.text_plus:
                appendOperator(OPERATOR_PULS);
                break;
            case R.id.text_equals:
                calculated();
                break;
            case R.id.text_0:
                appendNumber(NUMBER_ZERO);
                break;
            case R.id.text_1:
                appendNumber(NUMBER_ONE);
                break;
            case R.id.text_2:
                appendNumber(NUMBER_TWO);
                break;
            case R.id.text_3:
                appendNumber(NUMBER_THREE);
                break;
            case R.id.text_4:
                appendNumber(NUMBER_FOUR);
                break;
            case R.id.text_5:
                appendNumber(NUMBER_FIVE);
                break;
            case R.id.text_6:
                appendNumber(NUMBER_SIX);
                break;
            case R.id.text_7:
                appendNumber(NUMBER_SEVEN);
                break;
            case R.id.text_8:
                appendNumber(NUMBER_EIGHT);
                break;
            case R.id.text_9:
                appendNumber(NUMBER_NICE);
                break;
            case R.id.text_decimal:
                appendDecimal();
                break;
            default:
        }
    }

    private void setUpUi(View view) {
        mTextResult = view.findViewById(R.id.text_result);
        mTextReset = view.findViewById(R.id.text_ac);
        mTextPulsMinus = view.findViewById(R.id.text_plus_and_minus);
        mTextPercent = view.findViewById(R.id.text_percent);
        mTextDivide = view.findViewById(R.id.text_divide);
        mTextMultiply = view.findViewById(R.id.text_multiply);
        mTextMinus = view.findViewById(R.id.text_minus);
        mTextPlus = view.findViewById(R.id.text_plus);
        mTextEquals = view.findViewById(R.id.text_equals);
        mTextDecimal = view.findViewById(R.id.text_decimal);
        mTextNine = view.findViewById(R.id.text_9);
        mTextEight = view.findViewById(R.id.text_8);
        mTextSeven = view.findViewById(R.id.text_7);
        mTextSix = view.findViewById(R.id.text_6);
        mTextFive = view.findViewById(R.id.text_5);
        mTextFour = view.findViewById(R.id.text_4);
        mTextThree = view.findViewById(R.id.text_3);
        mTextTwo = view.findViewById(R.id.text_2);
        mTextOne = view.findViewById(R.id.text_1);
        mTextZero = view.findViewById(R.id.text_0);
    }

    private void setEvent() {
        mTextReset.setOnClickListener(this);
        mTextPulsMinus.setOnClickListener(this);
        mTextPercent.setOnClickListener(this);
        mTextDivide.setOnClickListener(this);
        mTextMultiply.setOnClickListener(this);
        mTextMinus.setOnClickListener(this);
        mTextPlus.setOnClickListener(this);
        mTextEquals.setOnClickListener(this);
        mTextDecimal.setOnClickListener(this);
        mTextNine.setOnClickListener(this);
        mTextEight.setOnClickListener(this);
        mTextSeven.setOnClickListener(this);
        mTextSix.setOnClickListener(this);
        mTextFive.setOnClickListener(this);
        mTextFour.setOnClickListener(this);
        mTextThree.setOnClickListener(this);
        mTextTwo.setOnClickListener(this);
        mTextOne.setOnClickListener(this);
        mTextZero.setOnClickListener(this);
    }

    public void saveResult() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFERENCES_KEY, mTextResult.getText().toString());
        editor.commit();
        Toast.makeText(getContext(), getString(R.string.text_saved), Toast.LENGTH_SHORT).show();
    }

    public void clearTextResult() {
        mTextResult.setText("0");
    }

    private void appendNumber(String number) {
        String temp = mTextResult.getText().toString();
        if (!temp.equals(NUMBER_ZERO)) mTextResult.setText(temp.concat(number));
        else mTextResult.setText(number);
    }

    private void oppositeNumber() {
        try {
            double temp = Double.parseDouble(mTextResult.getText().toString());
            temp = -temp;
            mTextResult.setText(formatNumber(temp));
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), getString(R.string.wrong_format), Toast.LENGTH_SHORT).show();
        }
    }

    private void percentNumber() {
        try {
            double temp = Double.parseDouble(mTextResult.getText().toString());
            temp = temp / 100;
            mTextResult.setText(formatNumber(temp));
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), getString(R.string.wrong_format), Toast.LENGTH_SHORT).show();
        }
    }

    private void getLastResult() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String lastResutl = sharedPreferences.getString(SHARED_PREFERENCES_KEY, NUMBER_ZERO);
        mTextResult.setText(lastResutl);
    }

    private String formatNumber(double number) {
        if (number == 0) return NUMBER_ZERO;
        else if (number == (int) number) return String.valueOf((int) number);
        else return String.valueOf(number);
    }

    private void appendOperator(String operator) {
        String temp = mTextResult.getText().toString();
        if (getOperator().equals("")) {
            mTextResult.setText(temp.concat(operator));
        } else {
            if (!calculated()) {
                temp = temp.substring(0, temp.length() - 1);
                mTextResult.setText(temp.concat(operator));
            } else {
                temp = mTextResult.getText().toString();
                mTextResult.setText(temp.concat(operator));
            }
        }
    }

    private void appendDecimal() {
        String temp = mTextResult.getText().toString();
        if (getOperator().equals("") && !temp.contains(NUMBER_DECIMAL)) {
            mTextResult.setText(temp.concat(NUMBER_DECIMAL));
        } else {
            String lastCharater = String.valueOf(temp.charAt(temp.length() - 1));
            if (!lastCharater.equals(getOperator())) {
                String[] math = temp.split("[+÷×−]");
                if (math.length == 2 && !math[1].contains(NUMBER_DECIMAL)) {
                    mTextResult.setText(temp.concat(NUMBER_DECIMAL));
                }
            }
        }
    }

    private String getOperator() {
        String temp = mTextResult.getText().toString();
        if (temp.contains(OPERATOR_PULS)) return OPERATOR_PULS;
        if (temp.contains(OPERATOR_MINUS)) return OPERATOR_MINUS;
        if (temp.contains(OPERATOR_MUL)) return OPERATOR_MUL;
        if (temp.contains(OPERATOR_DIV)) return OPERATOR_DIV;
        return "";
    }

    private boolean calculated() {
        String temp = mTextResult.getText().toString();
        String[] math = temp.split("[+÷×−]");
        double result = 0;
        String operator = getOperator();
        try {
            double mNumberOne = Double.parseDouble(math[0]);
            double mNumberTwo = Double.parseDouble(math[1]);
            switch (operator) {
                case OPERATOR_DIV:
                    if (mNumberTwo == 0) {
                        mTextResult.setText(getString(R.string.text_error_divide_zero));
                        return true;
                    } else result = mNumberOne / mNumberTwo;
                    break;
                case OPERATOR_MUL:
                    result = mNumberOne * mNumberTwo;
                    break;
                case OPERATOR_MINUS:
                    result = mNumberOne - mNumberTwo;
                    break;
                case OPERATOR_PULS:
                    result = mNumberOne + mNumberTwo;
                    break;
                default:
                    result = 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
        mTextResult.setText(formatNumber(result));
        return true;
    }
}
