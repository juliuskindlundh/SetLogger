package com.example.setlogger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BufferedSetOptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BufferedSetOptionsFragment extends Fragment {

    Button commitAllButton;
    Button deleteButton;
    public BufferedSetOptionsFragment() {}

    public static BufferedSetOptionsFragment newInstance() {
        BufferedSetOptionsFragment fragment = new BufferedSetOptionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buffered_set_options, container, false);

        buttonInit(view);

        return view;
    }

    private void buttonInit(View view) {
        commitAllButton = view.findViewById(R.id.buffered_set_options_commit_all);
        commitAllButton.setOnClickListener(commitAllOnClick());

        deleteButton = view.findViewById(R.id.buffered_set_options_delete);
        deleteButton.setOnClickListener(deleteOnClick());
    }

    private View.OnClickListener deleteOnClick() {
        return a->{

        };
    }

    private View.OnClickListener commitAllOnClick() {
        return a->{

        };
    }
}