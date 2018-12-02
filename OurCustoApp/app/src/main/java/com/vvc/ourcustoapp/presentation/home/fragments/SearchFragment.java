package com.vvc.ourcustoapp.presentation.home.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.presentation.location.activities.SelectSavedLocationAct;
import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.Constants;

import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SearchFragment extends Fragment implements TextWatcher, TextView.OnEditorActionListener {

    private AppCompatEditText search_edit_text;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initGui(view);
        return view;
    }

    private void initGui(View view) {
        search_edit_text = (AppCompatEditText) view.findViewById(R.id.search_edit_text);
        search_edit_text.setHint(getString(R.string.search_dishes));

        search_edit_text.addTextChangedListener(this);
        search_edit_text.setOnEditorActionListener(this);
        ((AppCompatImageButton) view.findViewById(R.id.search_voice_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AndroidUtils.promptSpeechToText(getActivity());
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                startActivityForResult(intent, Constants.REQUEST_SEARCH_MIC);

                //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
               /* } catch (ActivityNotFoundException a) {
                    //showMessage();
                    //Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_SEARCH_MIC:
                if (resultCode == RESULT_OK) {
                    List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = results.get(0);
                    search_edit_text.setText(spokenText);
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() < 3) {
            return;
        }
        onSearching(s.toString().trim());
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                String text = search_edit_text.getText().toString().trim();
                onSearching(text);
                return false;
        }
        return false;
    }

    private void onSearching(String value) {
        // startSearchTimer();
        // mSearchRunnable.setSearchName(value);
    }


}
