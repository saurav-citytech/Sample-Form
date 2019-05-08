package com.app.sampleform.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class FormAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SINGLE_FIELD = 1;
    private static final int DUAL_FIELD = 2;
    List<List<Section.Field>> fields;
    Context context;


    public FormAdpater(List<List<Section.Field>> fields) {
        this.fields = fields;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == SINGLE_FIELD) {
            return new SingleFieldViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_single_field, parent, false));
        } else {

            return new DualFieldViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_dual_field, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof SingleFieldViewHolder){
            ((SingleFieldViewHolder) holder).textInputLayout.setHint(fields.get(position).get(0).getDisplayLabel());
        }else if (holder instanceof DualFieldViewHolder){
            Section.Field firstField = fields.get(position).get(0);
            Section.Field secondField = fields.get(position).get(1);
            ((DualFieldViewHolder)holder).textInputLayout.setHint(firstField.getDisplayLabel());
            ((DualFieldViewHolder)holder).textInputLayout2.setHint(secondField.getDisplayLabel());

        }

    }

    @Override
    public int getItemCount() {

        return fields.size();
    }

    public class SingleFieldViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.et_field)
        TextInputEditText etField;
        @BindView(R.id.text_input_layout)
        TextInputLayout textInputLayout;
        @BindView(R.id.tv_description)
        AppCompatTextView tvDescription;

        public SingleFieldViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return fields.get(position).size();
    }

    public class DualFieldViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.et_field)
        TextInputEditText etField;
        @BindView(R.id.text_input_layout)
        TextInputLayout textInputLayout;
//        @BindView(R.id.et_field2)
        TextInputEditText etField2;
//        @BindView(R.id.text_input_layout2)
        TextInputLayout textInputLayout2;

        public DualFieldViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }
}
