package com.example.didaggerhilt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment


class MainFragment(
    private val someString: String
) : Fragment(R.layout.fragment_main) {

    private val TAG = "AppDebug"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Here is some string: ${someString}")
    }

}