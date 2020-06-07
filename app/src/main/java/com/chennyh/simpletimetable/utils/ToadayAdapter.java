package com.chennyh.simpletimetable.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.blankj.utilcode.util.ToastUtils;
import com.chennyh.simpletimetable.R;
import com.chennyh.simpletimetable.bean.Course;

import java.util.ArrayList;
import java.util.Objects;

public class ToadayAdapter extends ArrayAdapter<Course> {

    private Activity mActivity;
    private int mResource;
    private ArrayList<Course> courses;
    private Course course;
    private ListView mListView;

    public ToadayAdapter(Activity activity, ListView listView, int resource, ArrayList<Course> objects) {
        super(activity, resource, objects);
        mActivity = activity;
        mResource = resource;
        courses = objects;
        mListView = listView;
    }

    private static class ViewHolder {
        TextView className;
        TextView classTime;
        TextView classRoom;
        TextView classTeacher;
        ImageView classBtnPopup;
        CardView cardView;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String name = Objects.requireNonNull(getItem(position)).getName();
        String time = Objects.requireNonNull(getItem(position)).getTime();
        String room = Objects.requireNonNull(getItem(position)).getRoom();
        String teacher = Objects.requireNonNull(getItem(position)).getTeacher();
        int color = getItem(position).getColor();

        Course course = new Course(name, time, room, teacher, color);
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.className = convertView.findViewById(R.id.class_tv_name);
            holder.classTime = convertView.findViewById(R.id.class_tv_time);
            holder.classRoom = convertView.findViewById(R.id.class_tv_room);
            holder.classTeacher = convertView.findViewById(R.id.class_tv_teacher);
            holder.classBtnPopup = convertView.findViewById(R.id.class_btn_popup);
            holder.cardView = convertView.findViewById(R.id.class_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.className.setText(course.getName());
        holder.classTime.setText(course.getTime());
        holder.classRoom.setText(course.getRoom());
        holder.classTeacher.setText(course.getTeacher());
        holder.cardView.setCardBackgroundColor(course.getColor());
        holder.classBtnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(mActivity, holder.classBtnPopup);
                //TODO: read database

                popupMenu.getMenuInflater().inflate(R.menu.class_btn_edit, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_delete:
                                //TODO: impl
                                ToastUtils.showLong("delete");
                                return true;
                            case R.id.menu_edit:
                                //TODO: impl
                                ToastUtils.showLong("edit");
                                return true;
                            default:
                                return onMenuItemClick(item);
                        }
                    }
                });
                popupMenu.show();
            }
        });

        return convertView;
    }

}