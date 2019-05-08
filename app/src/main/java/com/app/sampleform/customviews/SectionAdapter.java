package com.app.sampleform.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    List<Section> sections;
    Context context;

    public SectionAdapter(List<Section> sections) {
        this.sections = sections;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SectionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.tvSectionHeader.setText(sections.get(position).getSectionHeader());
        holder.rvFields.setLayoutManager(new LinearLayoutManager(context));
        holder.rvFields.setAdapter(new FormAdpater(sections.get(position).getFields()));
        holder.rvFields.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_section_header)
        AppCompatTextView tvSectionHeader;
        @BindView(R.id.rv_fields)
        RecyclerView rvFields;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
