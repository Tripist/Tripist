package com.example.tripist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tripist.R;

public class SliderAdapter extends PagerAdapter {
    //Definition adapter variables
    Context context;
    LayoutInflater layoutInflater;

    // Constructor
    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.explore_ist,
            R.drawable.find_destination,
            R.drawable.get_information,
            R.drawable.add_favourite_list,
            R.drawable.get_suggestions

    };
    int headings[] = {
            R.string.first_slide_titel,
            R.string.second_slide_titel,
            R.string.third_slide_titel,
            R.string.fourth_slide_titel,
            R.string.fifth_slide_titel
    };
    int descriptions[] = {
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc,
            R.string.fifth_slide_desc
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        //Contents of screens that will slide
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading= view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
