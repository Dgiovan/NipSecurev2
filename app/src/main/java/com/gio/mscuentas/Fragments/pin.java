package com.gio.mscuentas.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gio.mscuentas.Enums.FragmentType;
import com.gio.mscuentas.Interfaces.OnFragmentInteractionListener;
import com.gio.mscuentas.R;
import com.gio.mscuentas.Utils.Constants;
import com.gio.mscuentas.Utils.KeyStoreHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class pin extends BaseFragmentListener implements View.OnClickListener {

    private final String TAG = pin.class.getSimpleName();

    private TextView numberOneTextView;
    private TextView numberTwoTextView;
    private TextView numberThreeTextView;
    private TextView numberFourTextView;
    private TextView numberFiveTextView;
    private TextView numberSixTextView;
    private TextView messageTextView;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonZero;
    private Button buttonBack;
    boolean isUnlocking = true;
    private String previousPin = "";
    private String currentPin = "";
    private int currentPosition = 0;
    private boolean cleanFieldsAfterUpdateText = false;

    public pin() {
        // Required empty public constructor
    }

    public static pin newInstance(OnFragmentInteractionListener onFragmentInteractionListener) {
        pin fragment = new pin();
        fragment.setOnFragmentInteractionListener(onFragmentInteractionListener);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            previousPin = getArguments().getString(getString(R.string.key_previous_pin), "");
            isUnlocking = getArguments().getBoolean(getString(R.string.key_is_unloking), true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container, false);
        numberOneTextView = v.findViewById(R.id.numberOneTextView);
        numberTwoTextView = v.findViewById(R.id.numberTwoTextView);
        numberThreeTextView = v.findViewById(R.id.numberThreeTextView);
        numberFourTextView = v.findViewById(R.id.numberFourTextView);
        numberFiveTextView = v.findViewById(R.id.numberFiveTextView);
        numberSixTextView = v.findViewById(R.id.numberSixTextView);
        messageTextView = v.findViewById(R.id.messageTextView);

        buttonOne = v.findViewById(R.id.buttonOne);
        buttonOne.setOnClickListener(this);
        buttonTwo = v.findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(this);
        buttonThree = v.findViewById(R.id.buttonThree);
        buttonThree.setOnClickListener(this);
        buttonFour = v.findViewById(R.id.buttonFour);
        buttonFour.setOnClickListener(this);
        buttonFive = v.findViewById(R.id.buttonFive);
        buttonFive.setOnClickListener(this);
        buttonSix = v.findViewById(R.id.buttonSix);
        buttonSix.setOnClickListener(this);
        buttonSeven = v.findViewById(R.id.buttonSeven);
        buttonSeven.setOnClickListener(this);
        buttonEight = v.findViewById(R.id.buttonEight);
        buttonEight.setOnClickListener(this);
        buttonNine = v.findViewById(R.id.buttonNine);
        buttonNine.setOnClickListener(this);
        buttonZero = v.findViewById(R.id.buttonZero);
        buttonZero.setOnClickListener(this);
        buttonBack = v.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);
        updateLineIndicator();
        setupMessage();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    // OnClickListener

    @Override
    public void onClick(View v) {
        if (isUnlocking && !canEnterPin()) return;
        String text = "";
        switch (v.getId()) {
            case R.id.buttonOne:
                text = "1";
                break;
            case R.id.buttonTwo:
                text = "2";
                break;
            case R.id.buttonThree:
                text = "3";
                break;
            case R.id.buttonFour:
                text = "4";
                break;
            case R.id.buttonFive:
                text = "5";
                break;
            case R.id.buttonSix:
                text = "6";
                break;
            case R.id.buttonSeven:
                text = "7";
                break;
            case R.id.buttonEight:
                text = "8";
                break;
            case R.id.buttonNine:
                text = "9";
                break;
            case R.id.buttonZero:
                text = "0";
                break;
            case R.id.buttonBack:
                break;
        }

        if (text.isEmpty()) {
            Log.d(TAG, "Borrar");
            if (currentPosition == 0) {
                return;
            }
            currentPosition--;
            currentPin = currentPin.substring(0, currentPosition);
            changeText(text);
        }
        else {
            changeText(text);
            currentPin += text;
            currentPosition++;
        }
        updateLineIndicator();

        if (currentPosition == 6) {
            if (isUnlocking) {
                evalutateWithSavedPin(currentPin);
            }
            else {
                if (previousPin.isEmpty()) {
                    showPinConfirmation(currentPin);
                    cleanFields();
                }
                else {
                    if (previousPin.equals(currentPin)) {
                        if (savePinToKeyStore(currentPin)) {
                            Log.d(TAG,"Everything ok, we can go to biometrics");
                            onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.COUNTS,false,null);
                        }
                    }
                    else {
                        messageTextView.setText("La clave es diferente a la anterior");
                        cleanFieldsAfterUpdateText = true;
                    }
                }
            }
        }
    }

    private void changeText(String text) {
        TextView textView = numberOneTextView;
        switch (currentPosition) {
            case 0:
                numberOneTextView.setText(text);
                textView = numberOneTextView;
                break;
            case 1:
                numberTwoTextView.setText(text);
                textView = numberTwoTextView;
                break;
            case 2:
                numberThreeTextView.setText(text);
                textView = numberThreeTextView;
                break;
            case 3:
                numberFourTextView.setText(text);
                textView = numberFourTextView;
                break;
            case 4:
                numberFiveTextView.setText(text);
                textView = numberFiveTextView;
                break;
            case 5:
                numberSixTextView.setText(text);
                textView = numberSixTextView;
                break;
        }

        if (!text.isEmpty()) {
            hideText(textView);
        }
    }

    private void updateLineIndicator() {
        numberOneTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        numberTwoTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        numberThreeTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        numberFourTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        numberFiveTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        numberSixTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
        switch (currentPosition) {
            case 0:
                numberOneTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
            case 1:
                numberTwoTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
            case 2:
                numberThreeTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
            case 3:
                numberFourTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
            case 4:
                numberFiveTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
            case 5:
                numberSixTextView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.bottom_line));
                break;
        }
    }

    private void hideText(final TextView textView) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("*");
                        if (cleanFieldsAfterUpdateText) {
                            cleanFields();
                            cleanFieldsAfterUpdateText = false;
                        }
                    }
                });
            }
        }, 150);
    }

    private void evalutateWithSavedPin(String pin) {
        String savedPin = KeyStoreHelper.getInstance().readPin();
        if (pin.equals(savedPin)) {
            handlePinAttempts(true);
            onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.COUNTS,false,null);

        }
        else {
            messageTextView.setText("Clave incorrecta");
            handlePinAttempts(false);
            cleanFieldsAfterUpdateText = true;
        }
    }

    private void cleanFields() {
        numberOneTextView.setText("");
        numberTwoTextView.setText("");
        numberThreeTextView.setText("");
        numberFourTextView.setText("");
        numberFiveTextView.setText("");
        numberSixTextView.setText("");
        currentPosition = 0;
        currentPin = "";
    }

    private void setupMessage() {
        if (isUnlocking) {
            messageTextView.setText("Ingresa tu clave para continuar");
        }
        else if (previousPin.isEmpty()) {
            messageTextView.setText("Define una clave para desbloquear la aplicación");
            Log.e("Saved pin?",previousPin);
        }
        else {
            messageTextView.setText("Confirma tu clave para desbloquear la aplicación");
        }
    }

    private boolean savePinToKeyStore(String pin) {
        return KeyStoreHelper.getInstance().savePin(pin);
    }

    private void showPinConfirmation(String pin) {
        Bundle args = new Bundle();
        args.putString(getString(R.string.key_previous_pin), pin);
        args.putBoolean(getString(R.string.key_is_unloking), false);
        onFragmentInteractionListener.onFragmentInteractionChangeFragment(FragmentType.PIN, true, args);
    }

    private void handlePinAttempts(boolean success) {
        SharedPreferences pref = getActivity().getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if (success) {
            edit.remove(getString(R.string.key_pin_attempts));
        }
        else {
            int pinAttempts = pref.getInt(getString(R.string.key_pin_attempts), 0);
            if (pinAttempts < Constants.Keys.maxPinAttempts - 1) {
                edit.putInt(getString(R.string.key_pin_attempts), pinAttempts + 1);
            }
            else {
                long currentTime = System.currentTimeMillis();
                edit.putLong(getString(R.string.key_blocked_timestamp), currentTime);
            }
        }
        edit.commit();
    }

    private boolean canEnterPin() {
        SharedPreferences pref = getActivity().getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
        long savedTime = pref.getLong(getString(R.string.key_blocked_timestamp), -1);
        if (savedTime == -1) {
            return true;
        }
        long difference = System.currentTimeMillis() - savedTime;
        long minutes = (difference / 1000) / 60;
        long seconds = (difference / 1000) % 60;
        if (minutes < Constants.Keys.blockedMinutes) {
            showAlertDialog("Error", "Debes esperar " + (Constants.Keys.blockedMinutes - minutes - 1) + " minutos " + (60 - seconds) + " segundos para volver a intentar.", "Ok", null);
            return false;
        }
        else {
            SharedPreferences.Editor edit = pref.edit();
            edit.remove(getString(R.string.key_blocked_timestamp));
            edit.remove(getString(R.string.key_pin_attempts));
            edit.commit();
            return true;
        }
    }

}
