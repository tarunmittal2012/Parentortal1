package com.example.tarunmittal.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunmittal.myapplication.Activity.AnnouncementActivity;
import com.example.tarunmittal.myapplication.AdapterClass.AnnouncementAdapter;
import com.example.tarunmittal.myapplication.AdapterClass.MarksAdapter;
import com.example.tarunmittal.myapplication.DataClass.Announcement;
import com.example.tarunmittal.myapplication.DataClass.Marks;
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
public class AnnouncementFragment extends android.support.v4.app.Fragment {

    String announcementJson;
    URL announcementUrl;
    AnnouncementAdapter mAdapter;
    ListView announcementList;
    TextView no_internet;
    ArrayList<Announcement>mAnnouncementArrayList=new ArrayList<>();
    public AnnouncementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_announcement, container, false);
        announcementList=view.findViewById(R.id.announcement_list);
        no_internet =view.findViewById(R.id.no_internet);
        getAnnouncementUrl();
        return view;

    }

    private void getAnnouncementUrl() {
        final ConnectivityManager conMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            SharedPreferences preferences = getActivity().getSharedPreferences("STUDENT", MODE_PRIVATE);
            String username = preferences.getString("ID", null);
            String attString = "http://parentsportal.000webhostapp.com/mobAnnouncement.php?Username=" + username;
            try {
                announcementUrl = new URL(attString);
                Log.e("MarksUrl", announcementUrl.toString());
                AnnouncementTask announcementTask = new AnnouncementTask();
                mAnnouncementArrayList = announcementTask.execute().get();
                announcementList.setAdapter(new AnnouncementAdapter(getActivity(), mAnnouncementArrayList));
                announcementList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Announcement announcement =mAnnouncementArrayList.get(i);
                        String date,type,detail,upload;
                        date = announcement.getDate();
                        type = announcement.getMessage();
                        detail =announcement.getDetail();
                        upload =announcement.getUploadBy();
                        Intent intent=new Intent(getActivity(),AnnouncementActivity.class);
                        intent.putExtra("detail",detail);
                        intent.putExtra("date",date);
                        intent.putExtra("type",type);
                        intent.putExtra("upload",upload);
                        startActivity(intent);
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            // notify user you are not online
            no_internet.setVisibility(View.VISIBLE);
            announcementList.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private class AnnouncementTask extends AsyncTask<Void,String,ArrayList<Announcement>>{

        @Override
        protected ArrayList<Announcement> doInBackground(Void... voids) {

            ArrayList<Announcement>announcements=null;
            try {
                announcementJson = NetworkUtils.httpConnection(announcementUrl);
                announcements = announcementsJsonParsing(announcementJson);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return announcements;
        }

        private ArrayList<Announcement> announcementsJsonParsing(String announcementJson) throws JSONException {
            ArrayList<Announcement> announcements = new ArrayList<>();
            final String TYPE="Type";
            final String DATE="Date";
            final String MESSAGE="Message";
            final String DETAIL="Detail";
            final String RES="res";
            final String UPLOADBY="Uploaded By";
            JSONObject studentJson = new JSONObject(announcementJson);
            JSONArray studentArray = studentJson.getJSONArray(RES);
            Log.e("AnnouncementFragment", studentArray.length() + "");
            for (int i = 0; i < studentArray.length(); i++) {
                JSONObject object = studentArray.getJSONObject(i);
                String type, dat, message,detail,upload;
                type = object.getString("" + TYPE);
                message = object.getString("" + MESSAGE);
                dat = object.getString("" + DATE);
                detail =object.getString(""+DETAIL);
                upload =object.getString(""+UPLOADBY);
                Announcement announcement = new Announcement(type,dat,message,detail,upload);
                announcements.add(announcement);
            }
            return announcements;

        }
    }
}
