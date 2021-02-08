package com.example.p12mapas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TolucaFragment extends DialogFragment {
    public static final String ARGUMENT0_TITLE="TITLE";
    public static final String ARGUMENTO_FULL_SNIPPET="FULL_SNIPPET";

    private String title;
    private String fullSnippet;

    public static TolucaFragment newInstance(String title, String fullSnippet){
        TolucaFragment fragment = new TolucaFragment();
        Bundle b =new Bundle();
        b.putString(ARGUMENT0_TITLE,title);
        b.putString(ARGUMENTO_FULL_SNIPPET,fullSnippet);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();
        title=args.getString(ARGUMENT0_TITLE);
        fullSnippet=args.getString(ARGUMENTO_FULL_SNIPPET);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(fullSnippet)
                .create();
        return dialog;
    }

}