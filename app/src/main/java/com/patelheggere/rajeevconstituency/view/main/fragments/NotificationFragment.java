package com.patelheggere.rajeevconstituency.view.main.fragments;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.base.BaseFragment;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static java.security.AccessController.getContext;

public class NotificationFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private View mView;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private Button sendMesage;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    private EditText editTextName, editTextPhone, editTextPlace, editTextPurpose;
    private ImageButton btnName, btnPhone, btnPlace, btnPurpose;

    public NotificationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_notification_layout, container, false);
        editTextName = mView.findViewById(R.id.textViewName);
        editTextPhone = mView.findViewById(R.id.textViewMobile);
        editTextPlace = mView.findViewById(R.id.textViewPlace);
        editTextPurpose = mView.findViewById(R.id.textViewPurpose);

        btnName = mView.findViewById(R.id.btnNameSpeak);
        btnPlace = mView.findViewById(R.id.btnPlaceSpeak);
        btnPhone = mView.findViewById(R.id.btnMobileSpeak);
        btnPurpose= mView.findViewById(R.id.btnPurposeSpeak);

        sendMesage = mView.findViewById(R.id.send);




        btnName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput(1);
            }
        });

        btnPlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput(2);
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput(3);
            }
        });


        btnPurpose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput(4);
            }
        });

        sendMesage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = mActivity.getPackageManager();
                //openWhatsappContact("919611620128");
                sendgroup();
            }
        });
        return mView;
    }

    void sendgroup()
    {
        if(editTextName.getText()==null || editTextName.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "Speak or type Name", Toast.LENGTH_LONG).show();
            return;
        }
        if(editTextPurpose.getText()==null || editTextPurpose.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "Speak or type Purpose", Toast.LENGTH_LONG).show();
            return;
        }
        if(editTextPhone.getText()==null || editTextPhone.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "Speak or type Phone Number", Toast.LENGTH_LONG).show();
            return;
        }
        if(editTextPlace.getText()==null || editTextPlace.getText().toString().trim().isEmpty())
        {
            Toast.makeText(getContext(), "Speak or type Place", Toast.LENGTH_LONG).show();
            return;
        }
        String text = "Name:"+editTextName.getText().toString() +"\nPlace:"+editTextPlace.getText().toString()+"\nContact Number:"+editTextPhone.getText().toString()+"\nPurpose:"+editTextPurpose.getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        mActivity.startActivity(sendIntent);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /**
     * Showing google speech input dialog
     * */
    private int SelectedType;
    private void promptSpeechInput(int type) {
        SelectedType = type;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        if(type!=3)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "kn-IN");

        try {
           startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    String textTodisplay="";
    /**
     * Receiving speech input
     * */
   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   String text = result.get(0);
                   if(text!=null) {
                       if (SelectedType == 1) {
                           editTextName.setText(text);
                       } else if (SelectedType == 2) {
                           editTextPlace.setText(text);
                       } else if (SelectedType == 3) {
                           text = text.replaceAll("\\s", "");
                           editTextPhone.setText(text.trim());
                       } else if (SelectedType == 4) {
                           editTextPurpose.setText(text);
                       }
                   }

                    //  txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    public String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^\\d]", " ");

        // Remove extra spaces from the beginning
        // and the ending of the string
        str = str.trim();

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");

        if (str.equals(""))
            return "-1";

        return str;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
