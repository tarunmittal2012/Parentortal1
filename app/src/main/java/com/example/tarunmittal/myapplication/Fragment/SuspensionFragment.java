package com.example.tarunmittal.myapplication.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tarunmittal.myapplication.AdapterClass.SuspensionAdapter;
import com.example.tarunmittal.myapplication.DataClass.Suspension;
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
public class SuspensionFragment extends android.support.v4.app.Fragment {

    String suspensionJson;

    URL suspensionUrl;

    ListView suspensionList;

    TextView no_internet,no_record;

    ArrayList<Suspension> mSuspensionArrayList = new ArrayList<>();

    public SuspensionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suspension, container, false);
        suspensionList = view.findViewById(R.id.suspention_list);
        no_internet = view.findViewById(R.id.no_internet);
        no_record =view.findViewById(R.id.no_record);
        getSuspensionUrl();
        return view;
    }

    private void getSuspensionUrl() {

        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            SharedPreferences preferences = getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
            String username = preferences.getString("ID", null);
            String attString = "http://parentsportal.000webhostapp.com/mobSuspension.php?Username=" + username;
            try {
                suspensionUrl = new URL(attString);
                Log.e("SuspensionURL:", suspensionUrl.toString());
                SuspensionTask suspensionTask = new SuspensionTask();
                mSuspensionArrayList = suspensionTask.execute().get();
                if (mSuspensionArrayList.size() > 0) {

                    suspensionList.setAdapter(new SuspensionAdapter(getActivity(), mSuspensionArrayList));
                }
                else if(mSuspensionArrayList.size()<=0){
                    no_record.setVisibility(View.VISIBLE);
                    suspensionList.setVisibility(View.VISIBLE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            no_record.setVisibility(View.GONE);
            suspensionList.setVisibility(View.GONE);
           // no_internet.setVisibility(View.VISIBLE);
        }
    }


    private class SuspensionTask extends AsyncTask<Void, String, ArrayList<Suspension>> {

        @Override
        protected ArrayList<Suspension> doInBackground(Void... voids) {

            ArrayList<Suspension> suspensions = null;
            try {
                suspensionJson = NetworkUtils.httpConnection(suspensionUrl);
                suspensions = suspensionJsonParsing(suspensionJson);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return suspensions;
        }

        private ArrayList<Suspension> suspensionJsonParsing(String suspensionJson) throws JSONException {

            ArrayList<Suspension> suspensionArrayList = new ArrayList<>();
            final String REASON = "reason";
            final String DATE = "date";
            final String RES = "res";
            final String FINE = "fine";
            JSONObject studentJson = new JSONObject(suspensionJson);
            JSONArray studentArray = studentJson.getJSONArray(RES);
            for (int i = 0; i < studentArray.length(); i++) {
                JSONObject object = studentArray.getJSONObject(i);
                String reason, date, fine;
                reason = object.getString(REASON);
                date = object.getString(DATE);
                fine = object.getString(FINE);
                Suspension suspension = new Suspension(reason, date, fine);
                suspensionArrayList.add(suspension);
            }
            return suspensionArrayList;
        }
    }
}
