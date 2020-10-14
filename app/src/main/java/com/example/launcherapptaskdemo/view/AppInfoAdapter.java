package com.example.launcherapptaskdemo.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appinfosdk2.ModalAppInfo;
import com.example.launcherapptaskdemo.R;
import com.example.launcherapptaskdemo.model.RowItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModalAppInfo> rowItems;
    private RecyclerOnRowItemClickListener mOnClickListener;

    AppInfoAdapter(List<ModalAppInfo> rowItems, RecyclerOnRowItemClickListener mClickListener) {
        this.mContext = mContext;
        this.rowItems = rowItems;
        this.mOnClickListener = mClickListener;
    }


    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_applist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder myViewHolder, int position) {

        ModalAppInfo item = rowItems.get(position);

        myViewHolder.txtAppName.setText(item.getAppName());
        myViewHolder.txtPkgName.setText(item.getPackages());

        if (item.getClassName().equals("")){
            myViewHolder.txtClassName.setVisibility(View.GONE);
        }else {
            myViewHolder.txtClassName.setVisibility(View.VISIBLE);
            myViewHolder.txtClassName.setText(item.getClassName());
        }
        myViewHolder.txtVerCode.setText(String.valueOf(item.getVersionCode()));
        myViewHolder.txtVerName.setText(item.getVersionName());

        myViewHolder.imgIcon.setImageDrawable(item.getIcon());

        myViewHolder.rowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onRowItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public void updateList(List<ModalAppInfo> rows) {
        this.rowItems = rows;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtAppName, txtPkgName, txtClassName, txtVerCode, txtVerName;
        de.hdodenhof.circleimageview.CircleImageView imgIcon;
        ConstraintLayout rowId;

        MyViewHolder(View itemView) {
            super(itemView);
            rowId = itemView.findViewById(R.id.rowId);
            imgIcon = itemView.findViewById(R.id.appIcon);

            txtAppName = itemView.findViewById(R.id.appName);
            txtPkgName = itemView.findViewById(R.id.appPkgName);
            txtClassName = itemView.findViewById(R.id.appClassName);
            txtVerCode = itemView.findViewById(R.id.appVerCode);
            txtVerName = itemView.findViewById(R.id.appVerName);
        }
    }
}
