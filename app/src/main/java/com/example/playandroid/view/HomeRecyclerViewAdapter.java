package com.example.playandroid.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playandroid.R;
import com.example.playandroid.entities.HomeTextItem;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> implements View.OnClickListener{
    List<HomeTextItem> list=new ArrayList<>();
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
    public HomeRecyclerViewAdapter ( List<HomeTextItem> list){
        this.list=list;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=(TextView) itemView.findViewById(R.id.home_recycler_view_test_view1);
            textView2=(TextView) itemView.findViewById(R.id.home_recycler_view_test_view2);
            textView3=(TextView) itemView.findViewById(R.id.home_recycler_view_test_view3);
            textView4=(TextView) itemView.findViewById(R.id.home_recycler_view_test_view4);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeTextItem homeTextItem=list.get(position);
        holder.textView1.setText("类型："+homeTextItem.getSuperChapterName()+"/"+homeTextItem.getChapterName());
        holder.textView2.setText("时间："+homeTextItem.getNiceDate());
        holder.textView3.setText(homeTextItem.getTitle());
        holder.textView4.setText("作者："+homeTextItem.getShareUser());
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
