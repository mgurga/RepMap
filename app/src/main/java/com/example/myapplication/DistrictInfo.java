package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import static com.example.myapplication.R.drawable.center;
import static com.example.myapplication.R.drawable.layout_bg;

public class DistrictInfo extends Activity {

    LinearLayout dislayout;
    JsonArray alldistricts;
    int districtnum;
    LinearLayout selectedlayout;
    String districtStateAbbr;
    ScrollView scoller;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.districtinfo);

        Intent intent = getIntent();
        JsonElement apijson = new Gson().fromJson(intent.getStringExtra("apiout"), JsonElement.class);

        Log.d("AAA", intent.getStringExtra("apiout"));

        alldistricts = apijson.getAsJsonObject().get("alldistricts").getAsJsonArray();
        districtnum = apijson.getAsJsonObject().get("districtnum").getAsInt();
        districtStateAbbr = apijson.getAsJsonObject().get("districtstate").getAsString();

        dislayout = findViewById(R.id.districtlayout);
        scoller = findViewById(R.id.scroller);
        //TextView tv1 = findViewById(R.id.alldistricts);
        //TextView tv2 = findViewById(R.id.districtnum);

        //tv1.setText(alldistricts.toString());
        //tv2.setText(districtnum + "");

        createDistricList();
    }

    public void createDistricList() {

        createDistricElement(districtnum);

        TextView otherDistricts = new TextView(this);
        otherDistricts.setText(getString(R.string.OtherDistricsIn) + " " + districtStateAbbr);
        otherDistricts.setTextSize(20);
        otherDistricts.setSingleLine(false);
        otherDistricts.setGravity(center);

        dislayout.addView(otherDistricts);

        for(int i = 0; i < alldistricts.size()-1; i++) {
            if(!(i == districtnum)) {
                if(!(i == 0)) {
                    ImageView divider = new ImageView(this);
                    LinearLayout.LayoutParams lp =
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(10, 10, 10, 10);
                    divider.setLayoutParams(lp);
                    divider.setBackgroundColor(Color.WHITE);
                    dislayout.addView(divider);
                }

                createDistricElement(i);
            }
        }
    }
    
    public void createDistricElement(int distnum) {

        LinearLayout.LayoutParams margin =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f);
        margin.setMargins(20, 10, 20, 10);

        LinearLayout eleLayout = new LinearLayout(this);
        eleLayout.setOrientation(LinearLayout.VERTICAL);
        eleLayout.setClipToOutline(true);
        eleLayout.setBackground(getResources().getDrawable(layout_bg, getTheme()));
        eleLayout.setLayoutParams(margin);

        JsonObject distdata = alldistricts.get(distnum).getAsJsonObject();

        LinearLayout.LayoutParams matchparent =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f);
        matchparent.setMargins(20, 0, 20, 0);

        if(distnum == districtnum) {
            eleLayout.setBackgroundColor(0xADD8E600);
            selectedlayout = eleLayout;
        }

        TextView title = new TextView(this);
        title.setText(distdata.get("num").toString().replaceAll("\"", "") + " " + getString(R.string.District));
        title.setTextSize(26);
        title.setLayoutParams(matchparent);
        title.setSingleLine(false);
        eleLayout.addView(title);

        TextView rep = new TextView(this);
        String originalname = distdata.get("name").toString().replaceAll("\"", "");
        String[] namesplit = originalname.split(",");
        rep.setText("Representative: " + namesplit[1] + " " + namesplit[0]);
        rep.setTextSize(14);
        rep.setSingleLine(false);
        rep.setLayoutParams(matchparent);
        eleLayout.addView(rep);

        TextView party = new TextView(this);
        String rawparty = distdata.get("party").toString().replaceAll("\"", "");
        String out = getString(R.string.PoliticalParty) + ": ";
        if(rawparty.equals("R")) {
            out += getString(R.string.Republican);
        } else if(rawparty.equals("D")) {
            out += getString(R.string.Democrat);
        } else {
            out += getString(R.string.OtherParty) + " (" + rawparty + ")";
        }
        party.setText(out);
        party.setTextSize(14);
        party.setSingleLine(false);
        party.setLayoutParams(matchparent);
        eleLayout.addView(party);

        TextView phone = new TextView(this);
        phone.setText(getString(R.string.PhoneNumber) + ": " + distdata.get("phone").toString().replaceAll("\"", ""));
        phone.setTextSize(14);
        phone.setSingleLine(false);
        phone.setLayoutParams(matchparent);
        eleLayout.addView(phone);

        TextView office = new TextView(this);
        office.setText(getString(R.string.OfficeRoom) + ": " + distdata.get("officeroom").toString().replaceAll("\"", ""));
        office.setTextSize(14);
        office.setSingleLine(false);
        office.setLayoutParams(matchparent);
        eleLayout.addView(office);

        TextView assign = new TextView(this);
        JsonArray assignlist = distdata.get("assignments").getAsJsonArray();
        String assignstr = "Assignments:\n";
        for(int i = 0; i < assignlist.size(); i++) {
            assignstr += "      " + assignlist.get(i).getAsString() + "\n";
        }
        assign.setTextSize(14);
        assign.setLayoutParams(matchparent);
        assign.setSingleLine(false);
        assign.setText(assignstr);
        eleLayout.addView(assign);

        dislayout.addView(eleLayout);
    }
}
