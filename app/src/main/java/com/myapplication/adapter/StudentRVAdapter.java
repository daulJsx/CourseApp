package com.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.R;
import com.myapplication.database.entities.StudentModal;

public class StudentRVAdapter extends ListAdapter<StudentModal, StudentRVAdapter.ViewHolder> {


    // creating a variable for on item click listener.
    private StudentRVAdapter.OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    public StudentRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<StudentModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<StudentModal>() {
        @Override
        public boolean areItemsTheSame(StudentModal oldItem, StudentModal newItem) {
            return oldItem.getStudentId() == newItem.getStudentId();
        }

        @Override
        public boolean areContentsTheSame(StudentModal oldItem, StudentModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getStudentName().equals(newItem.getStudentName()) &&
                    oldItem.getStudentPhone().equals(newItem.getStudentPhone()) &&
                    oldItem.getStartDate().equals(newItem.getStartDate()) &&
                    oldItem.getCourseDuration().equals(newItem.getCourseDuration());
        }
    };

    @NonNull
    @Override
    public StudentRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_rv_item, parent, false);
        return new StudentRVAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRVAdapter.ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        // TEXTVIEW DISESUAIKAN YA
        StudentModal model = getStudentAt(position);
        holder.courseNameTV.setText(model.getStudentName());
        holder.courseDescTV.setText(model.getStudentPhone());
        holder.courseDurationTV.setText(model.getStartDate());
        holder.courseDurationTV.setText(model.getCourseDuration());
    }

    // creating a method to get course modal for a specific position.
    public StudentModal getStudentAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView courseNameTV, courseDescTV, courseDurationTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            courseNameTV = itemView.findViewById(R.id.idTVStudentName);
            courseDescTV = itemView.findViewById(R.id.idTVStudentPhone);
            courseDurationTV = itemView.findViewById(R.id.idTVStartDate);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDuration);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(StudentModal model);
    }
    public void setOnItemClickListener(StudentRVAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    
}
