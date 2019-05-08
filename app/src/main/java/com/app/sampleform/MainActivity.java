package com.app.sampleform;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.app.sampleform.customviews.FormView;
import com.app.sampleform.model.Form;
import com.app.sampleform.model.Section;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.ll_form)
    LinearLayout llForm;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;

    FormView formView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Form form = new Gson().fromJson("{\"sections\":[{\"sectionHeader\":\"Personal\",\"fields\":[[{\"displayLabel\":\"First Name\",\"dataType\":\"DropDown\",\"defaultValue\":\"\",\"code\":200,\"isRequired\":true,\"values\":[{\"option\":\"Ram\",\"value\":\"Ram\"},{\"option\":\"Shyam\",\"value\":\"Shyam\"}]}],[{\"displayLabel\":\"Last Name\",\"dataType\":\"DropDown\",\"defaultValue\":\"\",\"code\":300,\"isRequired\":true,\"values\":[{\"option\":\"Shrestha\",\"value\":\"Shrestha\"},{\"option\":\"Shakya\",\"value\":\"Shakya\"}]}],[{\"displayLabel\":\"Date of Birth\",\"dataType\":\"Date\",\"defaultValue\":\"\",\"code\":400,\"isRequired\":true,\"values\":null}]]},{\"sectionHeader\":\"Contact\",\"fields\":[[{\"displayLabel\":\"Address\",\"dataType\":\"String\",\"defaultValue\":\"\",\"code\":500,\"isRequired\":true,\"values\":null}],[{\"displayLabel\":\"City\",\"dataType\":\"String\",\"defaultValue\":\"\",\"code\":600,\"isRequired\":true,\"values\":null},{\"displayLabel\":\"State\",\"dataType\":\"DropDown\",\"defaultValue\":\"\",\"code\":700,\"isRequired\":true,\"values\":[{\"option\":\"state1\",\"value\":\"State 1\"},{\"option\":\"state2\",\"value\":\"State 2\"},{\"option\":\"state3\",\"value\":\"State 3\"},{\"option\":\"state4\",\"value\":\"State 4\"}]}],[{\"displayLabel\":\"Phone\",\"dataType\":\"String\",\"defaultValue\":\"\",\"code\":800,\"isRequired\":false,\"values\":null},{\"displayLabel\":\"Mobile\",\"dataType\":\"String\",\"defaultValue\":\"\",\"code\":900,\"isRequired\":true,\"values\":null}],]}]}", Form.class);

        formView = new FormView(this) {
            @Override
            public List<Section> getSections() {
                return form.getSections();
            }
        };

        llForm.addView(formView);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        formView.onViewClicked();
    }
}
