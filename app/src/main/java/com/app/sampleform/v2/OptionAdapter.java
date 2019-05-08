package com.app.sampleform.v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

        List<Section.Value> values;
        OptionPickListener listener;

    public OptionAdapter(List<Section.Value> values, OptionPickListener listener) {
        this.values = values;
        this.listener = listener;
    }

    @NonNull
        @Override
        public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OptionViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
                holder.tvOption.setText(values.get(position).getValue());
        }

        @Override
        public int getItemCount() {
            return values.size();
        }

        public class OptionViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_option)
            AppCompatTextView tvOption;

            public OptionViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(v -> {
                    listener.onOptionPicked(values.get(getAdapterPosition()));
                });
            }
        }
    }