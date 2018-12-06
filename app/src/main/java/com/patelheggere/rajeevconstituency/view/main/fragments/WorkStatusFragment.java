package com.patelheggere.rajeevconstituency.view.main.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.base.BaseFragment;
import com.patelheggere.rajeevconstituency.model.BeneficiaryModel;
import com.patelheggere.rajeevconstituency.network.ApiInterface;
import com.patelheggere.rajeevconstituency.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkStatusFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private View mView;

    private static final String TAG = "WorkStatusFragment";

    private ApiInterface apiInterface;

    private TextInputEditText editText;
    private RadioGroup radioGroupType;
    private Button btnSubmit;
    private int type;

    public WorkStatusFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WorkStatusFragment newInstance(String param1, String param2) {
        WorkStatusFragment fragment = new WorkStatusFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpNetwork();
        mView =  inflater.inflate(R.layout.fragment_work_status, container, false);
        initView();
        return mView;
    }

    private void initView() {
        radioGroupType = mView.findViewById(R.id.rg_status);
        editText = mView.findViewById(R.id.status_number);
        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.mobile)
                {
                    type = 2;
                    editText.setHint("Enter Mobile Number");
                }
                else if(i==R.id.ack)
                {
                    type = 3;
                    editText.setHint("Enter ACK Number");
                }
                else if(i==R.id.epic)
                {
                    type = 1;
                    editText.setHint("Enter EPIC Number");
                }
            }
        });
        btnSubmit = mView.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type!=0)
                {
                    fetchStatus(type);
                }
            }
        });
    }

    private void fetchStatus(int type) {

        switch (type)
        {
            case 1:
                Call<BeneficiaryModel> beneficiaryModelCall = apiInterface.getByEPIC(editText.getText().toString());
                beneficiaryModelCall.enqueue(new Callback<BeneficiaryModel>() {
                    @Override
                    public void onResponse(Call<BeneficiaryModel> call, Response<BeneficiaryModel> response) {
                        if(response.body()!=null && response.body().getPurpose()!=null && response.body().getStatus()!=null)
                        {
                            Toast.makeText(mActivity, response.body().getPurpose()+" "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<BeneficiaryModel> call, Throwable t) {

                    }
                });
                break;
            case 2:
                Call<BeneficiaryModel> beneficiaryModelCall2 = apiInterface.getByMobile(editText.getText().toString());
                beneficiaryModelCall2.enqueue(new Callback<BeneficiaryModel>() {
                    @Override
                    public void onResponse(Call<BeneficiaryModel> call, Response<BeneficiaryModel> response) {
                        if(response.body()!=null && response.body().getPurpose()!=null && response.body().getStatus()!=null)
                        {
                            Toast.makeText(mActivity, response.body().getPurpose()+" "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<BeneficiaryModel> call, Throwable t) {

                    }
                });
                break;

            case 3:
                Call<BeneficiaryModel> beneficiaryModelCall3 = apiInterface.getByAck(editText.getText().toString());
                beneficiaryModelCall3.enqueue(new Callback<BeneficiaryModel>() {
                    @Override
                    public void onResponse(Call<BeneficiaryModel> call, Response<BeneficiaryModel> response) {
                        if(response.body()!=null && response.body().getPurpose()!=null && response.body().getStatus()!=null)
                        {
                            Toast.makeText(mActivity, response.body().getPurpose()+" "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<BeneficiaryModel> call, Throwable t) {

                    }
                });
                break;
        }

    }

    /*private void ShowAlert()
    {
        AlerDialog.Builder builder = new AlertDialog.Builder(mActivity);
    }*/

    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
         apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
