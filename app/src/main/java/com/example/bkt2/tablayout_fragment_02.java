package com.example.bkt2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class tablayout_fragment_02 extends Fragment {
    String urlGetdata = "http://192.168.119.1:81//kt_android/getdata.php";
    String urlDelete = "http://192.168.119.1:81/kt_android/delete.php";

    RecyclerView rvDanhsach;
    ArrayList<MonAnModel> arrayMonAn;
    MonAnAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tablayout_02, null);
        rvDanhsach = view.findViewById(R.id.recyclerView);
        arrayMonAn = new ArrayList<>();
        ReadJson(urlGetdata);
//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 1);
        rvDanhsach.setLayoutManager(manager1);
        adapter = new MonAnAdapter(tablayout_fragment_02.this, arrayMonAn);
        rvDanhsach.setAdapter(adapter);
        return view;
    }

    private void ReadJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            //Toast.makeText(getContext(),response.toString(), Toast.LENGTH_SHORT).show();
                arrayMonAn.clear();
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayMonAn.add(new MonAnModel(
                            object.getInt("id"),
                            object.getString("img_thumbnail"),
                            object.getString("tenmon"),
                            object.getInt("gia")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteMonAn(final int idmonA){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    ReadJson(urlGetdata);
                }else{
                    Toast.makeText(getContext(), "Lỗi xóa", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idcuamonan", String.valueOf(idmonA));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
