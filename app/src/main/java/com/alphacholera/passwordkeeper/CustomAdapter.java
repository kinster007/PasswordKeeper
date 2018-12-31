package com.alphacholera.passwordkeeper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<EntryItem> items;
    private Context context;

    CustomAdapter(Context context, ArrayList<EntryItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {
        final EntryItem item = items.get(position);
        myViewHolder.websiteName.setText(item.getWebsiteName());
        myViewHolder.dateAndTime.setText(item.getDate());
        // Add for the image
        if (myViewHolder.websiteName.getText().toString().isEmpty())
            myViewHolder.logo.setText("");
        else {
            StringBuilder builder = new StringBuilder();
            for (String string : myViewHolder.websiteName.getText().toString().split(" "))
                if ((string.charAt(0)>='a' && string.charAt(0)<='z') || (string.charAt(0)>='A' && string.charAt(0)<='Z'))
                    builder.append(string.charAt(0));
            myViewHolder.logo.setText(builder.toString());
        }
        myViewHolder.websiteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditOrViewItem.class);
                intent.putExtra("DateAndTimeIntent", myViewHolder.dateAndTime.getText());
                v.getContext().startActivity(intent);
            }
        });
        myViewHolder.dateAndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditOrViewItem.class);
                intent.putExtra("DateAndTimeIntent", myViewHolder.dateAndTime.getText());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // For ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView websiteName, dateAndTime, logo;
        // Add for the image
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.websiteName = itemView.findViewById(R.id.titleTextFieldRecyclerView);
            this.dateAndTime = itemView.findViewById(R.id.dateAndTimeTextFieldRecyclerView);
            // Add for the image
            this.logo = itemView.findViewById(R.id.logoRecyclerView);
        }
    }
}
