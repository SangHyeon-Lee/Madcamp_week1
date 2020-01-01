package com.example.madcamp_week1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReCyclerViewAdapter extends RecyclerView.Adapter<ReCyclerViewAdapter.MyViewHolder> {
    private ArrayList<Contact_view.Contact> myDataList = null;
    ReCyclerViewAdapter(ArrayList<Contact_view.Contact> dataList)
    {
        myDataList = dataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name, phone_number;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.contact_card_view);
            name = itemView.findViewById(R.id.contact_name);
            phone_number = itemView.findViewById(R.id.contact_phone_number);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ContactDetailView.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", myDataList.get(getAdapterPosition()));
                    intent.putExtras(bundle);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
    @Override
    public ReCyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.activity_contact_listitem_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position)
    {
        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.name.setText(myDataList.get(position).name);
        viewHolder.phone_number.setText(myDataList.get(position).phone_number);
    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        return myDataList.size();
    }
}
