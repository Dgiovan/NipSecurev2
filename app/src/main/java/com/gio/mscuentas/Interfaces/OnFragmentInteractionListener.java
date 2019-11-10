package com.gio.mscuentas.Interfaces;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gio.mscuentas.Enums.FragmentType;

public interface OnFragmentInteractionListener {

    void onFragmentInteractionChangeFragment(FragmentType fragmentType, boolean addToBackStack, @Nullable Bundle args);

}
