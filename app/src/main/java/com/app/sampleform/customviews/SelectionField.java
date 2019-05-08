package com.app.sampleform.customviews;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SelectionField extends TextInputLayout {

    @BindView(R.id.et_field)
    TextInputEditText etField;
    @BindView(R.id.input_layout)
    TextInputLayout inputLayout;

    public SelectionField(Context context) {
        super(context);
        initView();
    }

    public SelectionField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectionField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Section.Field field = setField();
        addView(inflate(getContext(), R.layout.selection_field, null));
        ButterKnife.bind(this);
        inputLayout.setHint(field.getDisplayLabel());
        setOnClickListener(v -> {
            selectOption(field.getValues());
        });
    }

    private void selectOption(List<Section.Value> values) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_options);
        RecyclerView rvOptions = dialog.findViewById(R.id.rv_options);
        rvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOptions.setAdapter(new OptionAdapter(values));
        dialog.show();

    }

    public abstract Section.Field setField();


    private class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

        List<Section.Value> values;

        public OptionAdapter(List<Section.Value> values) {
            this.values = values;
        }

        @NonNull
        @Override
        public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OptionViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return values.size();
        }

        private class OptionViewHolder extends RecyclerView.ViewHolder {

            public OptionViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(v -> {
                    etField.setText(values.get(getAdapterPosition()).getOption());
                });
            }
        }
    }

}
