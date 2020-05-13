package com.example.bkt2.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkt2.MainActivity;
import com.example.bkt2.R;

import java.util.HashMap;
import java.util.Map;

public class SlideshowFragment extends Fragment {
    EditText upLinkAnh, upTenMon, upGiaMon;
    TextView upTextid;
    Button btnUpdate;

    String urlUpdate = "http://192.168.1.236:81/kt_android/update.php";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gallery, null);
        upTextid = (TextView) view.findViewById(R.id.upTextid);
        upLinkAnh = (EditText) view.findViewById(R.id.upTextTenAnh);
        upTenMon = (EditText) view.findViewById(R.id.upTextTenMon);
        upGiaMon = (EditText) view.findViewById(R.id.upTextGia);
        btnUpdate = (Button) view.findViewById(R.id.buttonUpdate);
        Bundle bundle = getArguments();
        Toast.makeText(getContext(), "Vào trang cập nhật", Toast.LENGTH_LONG).show();

        if(bundle!=null){
            upTextid.setText(bundle.getString("id"));
            upLinkAnh.setText(bundle.getString("anh"));
            upTenMon.setText(bundle.getString("mon"));
            upGiaMon.setText(bundle.getString("gia"));
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenanh = upLinkAnh.getText().toString().trim();
                String tenmon = upTenMon.getText().toString().trim();
                String giamon = upGiaMon.getText().toString().trim();
                if (tenanh.matches("") && tenmon.matches("") && giamon.equals("")){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }else {
                    CapNhatMonAn(urlUpdate);
                }
            }
        });
        return view;
    }

    public void CapNhatMonAn(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }else{
                    Toast.makeText(getContext(), "Lỗi không cập nhật được", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Xảy ra lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "Lỗi!\n" + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idMonA", upTextid.getText().toString().trim());
                params.put("img_thumbnail", upLinkAnh.getText().toString().trim());
                params.put("tenmon", upTenMon.getText().toString().trim());
                params.put("giaMon", upGiaMon.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
