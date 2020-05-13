package com.example.bkt2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bkt2.ui.slideshow.SlideshowFragment;

import java.util.HashMap;
import java.util.Map;

public class tablayout_fragment_01 extends Fragment {
    EditText edtTenAnh, edtTenMon, edtGia;
    Button btnThem;
    String urlInsert = "http://192.168.119.1:81/kt_android/insert.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tablayout_01, null);
        edtTenAnh = (EditText) view.findViewById(R.id.editTextAnh);
        edtTenMon = (EditText) view.findViewById(R.id.editTextMonAn);
        edtGia    = (EditText) view.findViewById(R.id.editTextGia);
        btnThem   = (Button) view.findViewById(R.id.buttonThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                String tenanh   = edtTenAnh.getText().toString().trim();
                String tenmon   = edtTenMon.getText().toString().trim();
                String gia = edtTenMon.getText().toString().trim();

                if (tenanh.isEmpty() && tenmon.isEmpty() && gia.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }else {
                    ThemMonAn(urlInsert);
                }
            }
        });
        return view;
    }

    private void ThemMonAn(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
//                    FragmentManager fragmentManager=getParentFragmentManager();
//                    final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                    tablayout_fragment_02 tablayout_fragment_02= new tablayout_fragment_02();
//                    fragmentTransaction.add(R.id.homeviewpp, tablayout_fragment_02);
//                    fragmentTransaction.commit();

                    startActivity(new Intent(getContext(), MainActivity.class));

                }else{
                    Toast.makeText(getContext(), "Lỗi không thêm được", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Xảy ra lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "Lỗi!\n" + error.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("img_thumbnail", edtTenAnh.getText().toString().trim());
                params.put("tenMon", edtTenMon.getText().toString().trim());
                params.put("giaMon", edtGia.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
