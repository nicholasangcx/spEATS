package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.speats.R;

public class UpdateFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    int smallNumber = 5;
    int mediumNumber = 5;
    int largeNumber = 5;

    public UpdateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance() {
        UpdateFragment fragment = new UpdateFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update, container, false);


        Button a = (Button) v.findViewById(R.id.increaseSmall);
        a.setOnClickListener(this);

        Button b = (Button) v.findViewById(R.id.decreaseSmall);
        b.setOnClickListener(this);

        Button c = (Button) v.findViewById(R.id.increaseMedium);
        c.setOnClickListener(this);

        Button d = (Button) v.findViewById(R.id.decreaseMedium);
        d.setOnClickListener(this);

        Button e = (Button) v.findViewById(R.id.increaseLarge);
        e.setOnClickListener(this);

        Button f = (Button) v.findViewById(R.id.decreaseLarge);
        f.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.increaseSmall:
                smallNumber++;
                displaySmall(smallNumber);
                break;
            case R.id.decreaseSmall:
                smallNumber--;
                displaySmall(smallNumber);
                break;
            case R.id.increaseMedium:
                mediumNumber++;
                displayMedium(mediumNumber);
                break;
            case R.id.decreaseMedium:
                mediumNumber--;
                displayMedium(mediumNumber);
                break;
            case R.id.increaseLarge:
                largeNumber++;
                displayLarge(largeNumber);
                break;
            case R.id.decreaseLarge:
                largeNumber--;
                displayLarge(largeNumber);
                break;
        }
    }

    private void displaySmall(int number) {
        TextView displayInteger = (TextView) getActivity().findViewById(R.id.smallNumber);
        displayInteger.setText("" + number);
    }

    private void displayMedium(int number) {
        TextView displayInteger = (TextView) getActivity().findViewById(R.id.mediumNumber);
        displayInteger.setText("" + number);
    }

    private void displayLarge(int number) {
        TextView displayInteger = (TextView) getActivity().findViewById(R.id.largeNumber);
        displayInteger.setText("" + number);
    }

}





