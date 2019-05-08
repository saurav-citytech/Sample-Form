package com.app.sampleform.v2;

import android.app.VoiceInteractor;

import com.app.sampleform.model.Section;

public interface OptionPickListener {
    public void onOptionPicked(Section.Value value);
}
