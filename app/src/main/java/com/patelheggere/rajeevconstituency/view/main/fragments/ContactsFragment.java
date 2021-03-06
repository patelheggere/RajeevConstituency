package com.patelheggere.rajeevconstituency.view.main.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.patelheggere.rajeevconstituency.R;
import com.patelheggere.rajeevconstituency.adapter.UsersAdapter;
import com.patelheggere.rajeevconstituency.base.BaseFragment;
import com.patelheggere.rajeevconstituency.model.BeneficiaryModel;
import com.patelheggere.rajeevconstituency.model.VillageModel;
import com.patelheggere.rajeevconstituency.network.ApiInterface;
import com.patelheggere.rajeevconstituency.network.RetrofitInstance;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private View mView;
    private Spinner villageSpinner;
    private List<String> villageName;
    private RecyclerView recyclerView;
    private TextView textViewTotal;
    private ProgressBar progressBar;
    private RadioGroup radioGroup;
    private LinearLayout village_data, boothDetails;
    private EditText editTextBoothNo;
    private Button buttonGetData;
    private String selectedType = "leaders";

    public ContactsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
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
        mView =  inflater.inflate(R.layout.fragment_contacts, container, false);
        villageSpinner = mView.findViewById(R.id.village_spinner);
        recyclerView = mView.findViewById(R.id.beneficaryRecyclerView);
        progressBar = mView.findViewById(R.id.progressbar);
        textViewTotal = mView.findViewById(R.id.textViewTotal);
        radioGroup = mView.findViewById(R.id.rg_status);
        village_data = mView.findViewById(R.id.village_data);
        boothDetails = mView.findViewById(R.id.booth_data);
        editTextBoothNo = mView.findViewById(R.id.editText_booth_no);
        buttonGetData = mView.findViewById(R.id.get_booth_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);

        setUpNetwork();
        initListeners();
        getVillages();
        return mView;
    }

    private void initListeners() {
        villageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(selectedType.equalsIgnoreCase("leaders")){
                    getDataByvillage(villageName.get(position), 2);
                }
                else {
                    getDataByvillage(villageName.get(position), 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.beneficary)
                {
                    village_data.setVisibility(View.VISIBLE);
                    boothDetails.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    if(villageName!=null && villageName.size()>0) {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, villageName);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        villageSpinner.setAdapter(dataAdapter);
                    }
                    selectedType = "beneficiary";
                }
                else if(checkedId==R.id.leaders){
                    village_data.setVisibility(View.VISIBLE);
                    boothDetails.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    selectedType = "leaders";
                }
                else
                {
                    recyclerView.setVisibility(View.GONE);
                    village_data.setVisibility(View.INVISIBLE);
                    boothDetails.setVisibility(View.VISIBLE);
                    editTextBoothNo.setText("");
                }
            }
        });
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextBoothNo.getText()!=null && editTextBoothNo.getText().length()>0)
                {
                    getBoothData(Integer.parseInt(editTextBoothNo.getText().toString()));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1 : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }
    private void getBoothData(int number) {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<BeneficiaryModel>> listCall = apiInterface.getboothLeaders(number);
        recyclerView.setVisibility(View.GONE);
        listCall.enqueue(new Callback<List<BeneficiaryModel>>() {
            @Override
            public void onResponse(Call<List<BeneficiaryModel>> call, Response<List<BeneficiaryModel>> response) {
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        textViewTotal.setText("Total:"+response.body().size());
                        UsersAdapter usersAdapter = new UsersAdapter(getContext(), response.body());
                        recyclerView.setAdapter(usersAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "No data found for this village", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BeneficiaryModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Some thing wrong while fetching data", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getDataByvillage(String s, int type) {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<BeneficiaryModel>> listCall = apiInterface.getByDataByVillage(s, type+"");
        recyclerView.setVisibility(View.GONE);
        listCall.enqueue(new Callback<List<BeneficiaryModel>>() {
            @Override
            public void onResponse(Call<List<BeneficiaryModel>> call, Response<List<BeneficiaryModel>> response) {
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        textViewTotal.setText("Total:"+response.body().size());
                        UsersAdapter usersAdapter = new UsersAdapter(getContext(), response.body());
                        recyclerView.setAdapter(usersAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getContext(), "No data found for this village", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BeneficiaryModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Some thing wrong while fetching data", Toast.LENGTH_LONG).show();

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void getVillages(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<VillageModel>> listCall = apiInterface.getVillage("1", "1");
        listCall.enqueue(new Callback<List<VillageModel>>() {
            @Override
            public void onResponse(Call<List<VillageModel>> call, Response<List<VillageModel>> response) {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        villageName = new ArrayList<>();
                        for(int i=0;i<response.body().size(); i++){
                            villageName.add(response.body().get(i).getName());
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, villageName);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        villageSpinner.setAdapter(dataAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VillageModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Some thing wrong while fetching data", Toast.LENGTH_LONG).show();
            }
        });
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
        void onFragmentInteraction(Uri uri);
    }

    private ApiInterface apiInterface;

    private void setUpNetwork()
    {
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.setClient();
        apiInterface = retrofitInstance.getClient().create(ApiInterface.class);
    }
}
