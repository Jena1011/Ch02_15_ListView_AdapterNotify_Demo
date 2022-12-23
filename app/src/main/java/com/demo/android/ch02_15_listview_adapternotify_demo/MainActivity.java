package com.demo.android.ch02_15_listview_adapternotify_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc_view;
    RecyclerView.LayoutManager layoutManager;
    private LinkedList<HashMap<String,String>> data;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc_view = findViewById(R.id.rv_view);
        layoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(layoutManager);
        prepare_data();
        MyAdapter myAdapter = new MyAdapter(); // 直接存取外層類別成員變數data，所以不用參數
        rc_view.setAdapter(myAdapter);

    }

    private void prepare_data() {
        data = new LinkedList<>();
        for (int i = 0; i<50; i++){
            HashMap<String,String> row = new HashMap<>();
            int rand = (int)(Math.random()*31+1);
            row.put("title","第"+i+"項");
            row.put("date","Date"+rand);
            data.add(row);
        }
    }

    public void modifyItem(View view) {
        data.get(5).put("title","jena");
        data.get(5).put("date","2022/12/"+(int)(Math.random()*31+1));
        myAdapter.notifyDataSetChanged();
    }

    public void removeFirstItem(View view) {
        data.removeFirst();
        myAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> { // 設為內部類別可避免不必要參數傳送(data)

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv_title, tv_date;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_date = itemView.findViewById(R.id.tv_date);
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            MyAdapter.MyViewHolder vh = new MyViewHolder(itemView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
            holder.tv_title.setText(data.get(position).get("title"));
            holder.tv_date.setText(data.get(position).get("date"));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


}