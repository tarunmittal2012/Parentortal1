package com.example.tarunmittal.myapplication.Fragment;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.AdapterClass.MarksAdapter;
import com.example.tarunmittal.myapplication.DataClass.Marks;
import com.example.tarunmittal.myapplication.Networking.NetworkUtils;
import com.example.tarunmittal.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;
/**
 * A simple {@link Fragment} subclass.
 */
public class MarksFragment extends Fragment {

    String marksJSON;

    ListView marksListView;

    MarksAdapter mAdapter;

    TextView no_internet;

    private URL marksURL;

    private ArrayList<Marks> myMarksList = new ArrayList<>();

    public MarksFragment() {
        // Required empty public constructor
    }

    private static ArrayList<Marks> jsonParsingForMarks(String jsonResponse) throws JSONException {

        ArrayList<Marks> marksArrayList = new ArrayList<>();
        final String SECTION = "section";
        final String MENTOR_REG = "treg_no";
        final String SUBJECT = "subject";
        final String DATE = "date";
        final String MARKS = "marks";
        final String RES = "res";
        final String TEST_TYPE = "test_type";
        JSONObject studentJson = new JSONObject(jsonResponse);
        JSONArray studentArray = studentJson.getJSONArray(RES);
        Log.e("MarksFragmnet", studentArray.length() + "");
        for (int i = 0; i < studentArray.length(); i++) {
            Log.e("MarksFragmnet", studentArray.length() + "");
            JSONObject object = studentArray.getJSONObject(i);
            String mentor_reg, marks, dat, subject, test_type, section;
            mentor_reg = object.getString("" + MENTOR_REG);
            marks = object.getString("" + MARKS);
            dat = object.getString("" + DATE);
            subject = object.getString("" + SUBJECT);
            test_type = object.getString("" + TEST_TYPE);
            section = object.getString("" + SECTION);
            Log.e("MarksFragment", mentor_reg + " " + "\n"
                    + " " + dat + " " +
                    subject + " " + test_type + " " +
                    section);
            Marks marksObject = new Marks(section, mentor_reg, subject, dat, test_type, marks);
            marksArrayList.add(marksObject);
        }
        return marksArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marks, container, false);
        marksListView = view.findViewById(R.id.myMarks_view);
        no_internet = view.findViewById(R.id.no_internet);
        getMarksUrl();

        return view;

    }

    private void getMarksUrl() {

        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            SharedPreferences preferences = getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
            String username = preferences.getString("ID", null);
            String attString = "http://parentsportal.000webhostapp.com/mobMarks.php?UserName=" + username;
            try {
                marksURL = new URL(attString);
                Log.e("MarksUrl", marksURL.toString());
                MarksTask marksTask = new MarksTask();
                myMarksList = marksTask.execute().get();
                marksListView.setAdapter(new MarksAdapter(getActivity(), myMarksList));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            // notify user you are not online
            marksListView.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    class MarksTask extends AsyncTask<Void, String, ArrayList<Marks>> {

        @Override
        protected ArrayList<Marks> doInBackground(Void... voids) {

            ArrayList<Marks> att = null;
            try {
                marksJSON = NetworkUtils.httpConnection(marksURL);
                att = jsonParsingForMarks(marksJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return att;
        }
    }
}




