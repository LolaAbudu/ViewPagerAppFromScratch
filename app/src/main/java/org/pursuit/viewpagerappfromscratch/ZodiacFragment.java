package org.pursuit.viewpagerappfromscratch;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ZodiacFragment extends Fragment {

    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String IMAGE = "image";

    private String name;
    private String number;
    private String image;

    private TextView nameTextView;
    private TextView numberTextView;
    private ImageView imageView;


    public ZodiacFragment() {
        // Required empty public constructor
    }

    public static ZodiacFragment newInstance(String name, String number, String image) {
        ZodiacFragment fragment = new ZodiacFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(NUMBER, number);
        args.putString(IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            number = getArguments().getString(NUMBER);
            image = getArguments().getString(IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zodiac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTextView = view.findViewById(R.id.name_text_view);
        numberTextView = view.findViewById(R.id.number_text_view);
        imageView = view.findViewById(R.id.image_view);

        nameTextView.setText(name);
        numberTextView.setText(number);
        Picasso.get().load(image).into(imageView);
    }
}
