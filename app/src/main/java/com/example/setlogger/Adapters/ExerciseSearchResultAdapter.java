package com.example.setlogger.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.R;
import com.example.setlogger.repository.dto.ExerciseDTO;

import java.util.List;

public class ExerciseSearchResultAdapter extends RecyclerView.Adapter<ExerciseSearchResultAdapter.ViewHolder> {
    private List<ExerciseDTO> searchResults;

    private TextView textView;
    public ExerciseSearchResultAdapter(List<ExerciseDTO> list, TextView textView){
        this.searchResults = list;
        this.textView = textView;
    }
    @Override
    public ExerciseSearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_list_item,parent,false);
        return new ExerciseSearchResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseSearchResultAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(searchResults.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.search_result_text_view);
            nameTextView.setOnClickListener(nameTextViewOnClick(this));
        }
    }
    private View.OnClickListener nameTextViewOnClick(ViewHolder viewHolder) {
        return a->{
            this.textView.setText(viewHolder.nameTextView.getText());
        };
    }
}
