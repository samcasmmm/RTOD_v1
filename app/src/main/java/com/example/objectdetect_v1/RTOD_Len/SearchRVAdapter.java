package com.example.objectdetect_v1.RTOD_Len;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.objectdetect_v1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder>{

    private Context context;
    private ArrayList<SearchRVModal> searchRVModals;

    public SearchRVAdapter(Context context, ArrayList<SearchRVModal> searchRVModals) {
        this.context = context;
        this.searchRVModals = searchRVModals;
    }

    @NonNull
    @Override
    public SearchRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_results_rev_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRVAdapter.ViewHolder holder, int position) {
        SearchRVModal searchRVModal = searchRVModals.get(position);
        holder.titleTV.setText(searchRVModal.getTitle());
        holder.descriptionTV.setText(searchRVModal.getSnippet());
        holder.snippetTV.setText(searchRVModal.getLink());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(searchRVModal.getLink()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTV,snippetTV,descriptionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVTitle);
            snippetTV = itemView.findViewById(R.id.idTVSnippet);
            descriptionTV = itemView.findViewById(R.id.idTVDescription);



        }
    }
    }
