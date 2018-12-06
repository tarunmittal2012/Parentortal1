package com.example.tarunmittal.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.Activity.AnnouncementActivity;
import com.example.tarunmittal.myapplication.Activity.PlacementActivity;
import com.example.tarunmittal.myapplication.AdapterClass.PlacementAdapter;
import com.example.tarunmittal.myapplication.DataClass.Placement;
import com.example.tarunmittal.myapplication.Networking.NetworkUtils;
import com.example.tarunmittal.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;
/**
 * A simple {@link Fragment} subclass.
 */
public class PlacementFragment extends Fragment {

    String placementJson;

    TextView no_internet;

    URL placementUrl;

    ListView companyList;

    ArrayList<Placement> mPlacementArrayList = new ArrayList<>();

    PlacementAdapter mAdapter;

    public PlacementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_placement, container, false);
        companyList = view.findViewById(R.id.placement_list);
        no_internet = view.findViewById(R.id.no_internet);
        getPlacementUrl();
        return view;
    }

    private ArrayList<Placement> placementJsonParsing(String placementJson) throws JSONException {

        ArrayList<Placement> placementArrayList = new ArrayList<>();
        final String COMPANY_NAME = "company_name";
        final String IS_ELIGIBLE = "is_eligible";
        final String IS_SELECTED = "is_selected";
        final String DATE = "date";
        final String JOB_LOCATION="location";
        final String JOB_PROFILE="job_profle";
        final String SALARY = "salary";
        final String ABOUT= "about";
        final String RES = "res";
        JSONObject studentJson = new JSONObject(placementJson);
        JSONArray studentArray = studentJson.getJSONArray(RES);
        for (int i = 0; i < studentArray.length(); i++) {
            JSONObject object = studentArray.getJSONObject(i);
            String companyName, isEligible, isSelecetd, date, jobLocation,jobProfile,salary,about;
            companyName = object.getString(COMPANY_NAME);
            isEligible = object.getString(IS_ELIGIBLE);
            isSelecetd = object.getString(IS_SELECTED);
            date = object.getString(DATE);
            jobLocation= object.getString(JOB_LOCATION);
            jobProfile =object.getString(JOB_PROFILE);
            salary = object.getString(SALARY);
            about= object.getString(ABOUT);
            Placement placement =new Placement(companyName,date,isSelecetd,isEligible,jobProfile,salary,jobLocation,about);
            placementArrayList.add(placement);
        }
        return placementArrayList;
    }

    private void getPlacementUrl() {

        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            SharedPreferences preferences = getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
            String username = preferences.getString("ID", null);
            String attString = "http://parentsportal.000webhostapp.com/mobPlacement.php?Username=" + username;
            try {
                placementUrl = new URL(attString);
                Log.e("AtttendenceUrl", placementUrl.toString());
                PlacementTask placementTask = new PlacementTask();
                mPlacementArrayList = placementTask.execute().get();
                companyList.setAdapter(new PlacementAdapter(getActivity(), mPlacementArrayList));
                companyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Placement placement=mPlacementArrayList.get(i);
                        String companyName, isEligible, isSelecetd, date, jobLocation,jobProfile,salary,about;

                        companyName =placement.getcName();
                        isEligible =placement.getIsEligible();
                        isSelecetd =placement.getIsSelected();
                        date = placement.getDate();
                        jobLocation= placement.getLocation();
                        jobProfile=placement.getJobProfile();
                        salary= placement.getSalary();
                        about=placement.getAbout();
                        Intent intent=new Intent(getActivity(), PlacementActivity.class);
                        intent.putExtra("cname",companyName);
                        intent.putExtra("isEligible",isEligible);
                        intent.putExtra("isSelecetd",isSelecetd);
                        intent.putExtra("date",date);
                        intent.putExtra("jobLocation",jobLocation);
                        intent.putExtra("jobProfile",jobProfile);
                        intent.putExtra("salary",salary);
                        intent.putExtra("about",about);

                        startActivity(intent);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            // notify user you are not online
            no_internet.setVisibility(View.VISIBLE);
            companyList.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }

    }

    private class PlacementTask extends AsyncTask<Void, String, ArrayList<Placement>> {

        @Override
        protected ArrayList<Placement> doInBackground(Void... voids) {

            ArrayList<Placement> placements = null;
            try {
                placementJson = NetworkUtils.httpConnection(placementUrl);
                placements = placementJsonParsing(placementJson);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return placements;
        }
    }
}
