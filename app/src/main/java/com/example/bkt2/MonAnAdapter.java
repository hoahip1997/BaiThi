package com.example.bkt2;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bkt2.ui.slideshow.SlideshowFragment;

import java.io.Serializable;
import java.util.ArrayList;
public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHoder>{
    tablayout_fragment_02 context;
    ArrayList<MonAnModel> MonAn;
    public MonAnAdapter(tablayout_fragment_02 context, ArrayList<MonAnModel> monAn) {
        this.context = context;
        this.MonAn = monAn;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, null);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoder holder, final int position) {
        holder.tvTenMon.setText(MonAn.get(position).getTenmon());
        holder.tvGia.setText(String.valueOf(MonAn.get(position).getGia()));
        Glide.with(context).load(MonAn.get(position).getImg_thumbnail())
                .centerCrop()
                .placeholder(R.drawable.daonia)
                .error(R.drawable.daonia2)
                .into(holder.img_thumbnail);

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager= context.getParentFragmentManager();
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                SlideshowFragment slideshowFragment= new SlideshowFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(MonAn.get(position).getId()));
                bundle.putString("mon", MonAn.get(position).getTenmon());
                bundle.putString("gia", String.valueOf(MonAn.get(position).getGia()));
                bundle.putString("anh", MonAn.get(position).getImg_thumbnail());
                slideshowFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.homeviewpp, slideshowFragment, null);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        holder.cardviewitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialogXoa = new AlertDialog.Builder(view.getContext());
                dialogXoa.setMessage("Bạn có muốn xóa món " +"'"+MonAn.get(position).getTenmon() + "'" + " không ?");
                dialogXoa.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialogXoa.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.DeleteMonAn( MonAn.get(position).getId());
                    }
                });
                dialogXoa.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return MonAn.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        CardView cardviewitem;
        TextView tvTenMon, tvGia;
        ImageView imgEdit, img_thumbnail;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            this.cardviewitem = itemView.findViewById(R.id.cardviewitem);
            this.tvTenMon = itemView.findViewById(R.id.tvTenMon);
            this.tvGia = itemView.findViewById(R.id.tvGia);
            this.imgEdit = itemView.findViewById(R.id.imgEdit);
            this.img_thumbnail = itemView.findViewById(R.id.img_thumbnail);
        }
    }
}
