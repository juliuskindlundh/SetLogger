package com.example.setlogger.Adapters;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.setlogger.Activities.BufferedSetsActivity;
import com.example.setlogger.R;
import com.example.setlogger.repository.dto.BufferedSetDTO;

import java.util.Date;
import java.util.List;

public class BufferedSetAdapter extends RecyclerView.Adapter<BufferedSetAdapter.ViewHolder> {

    List<BufferedSetDTO> bufferedSetDTOList;
    BufferedSetsActivity bufferedSetsActivity;

    public BufferedSetAdapter(List<BufferedSetDTO> bufferedSetDTOList, BufferedSetsActivity bufferedSetsActivity){
        this.bufferedSetDTOList = bufferedSetDTOList;
        this.bufferedSetsActivity = bufferedSetsActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public BufferedSetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buffered_set_layout, parent, false);
        return new BufferedSetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BufferedSetAdapter.ViewHolder holder, int position) {
        holder.exerciseTextView.setText(getExerciseNameFromDB(bufferedSetDTOList.get(position).getExercise_id()));
        holder.weightTextView.setText(String.valueOf(bufferedSetDTOList.get(position).getWeight()));
        holder.repsTextView.setText(String.valueOf(bufferedSetDTOList.get(position).getReps()));
        holder.setDateTime(bufferedSetDTOList.get(position).getTime());
        holder.dto = bufferedSetDTOList.get(position);

        if(bufferedSetsActivity.getSelectedId() == holder.dto.getId()){
            holder.setColour(R.color.selectedColour);
        }
        else{
            holder.setColour(R.color.design_default_color_background);
        }
    }

    private String getDateString(long time) {
        Date d = new Date(time);
        return d.toString();
    }

    @Override
    public int getItemCount() {
        return bufferedSetDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        BufferedSetDTO dto;
        TextView exerciseTextView;
        TextView weightTextView;
        TextView repsTextView;
        TextView dateTimeTextView;

        int newDay;
        int newMonth;
        int newYear;
        int newHour;
        int newMinute;

        int currentHour;
        int currentMinute;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(View itemView) {
            super(itemView);
            exerciseTextView = itemView.findViewById(R.id.buffered_set_exercise);
            weightTextView = itemView.findViewById(R.id.buffered_set_weight);
            repsTextView = itemView.findViewById(R.id.buffered_set_reps);
            dateTimeTextView = itemView.findViewById(R.id.buffered_set_time);
            dateTimeTextView.setOnClickListener(dateTimeOnClick());
            itemView.setOnClickListener(itemViewOnClick());
        }

        private View.OnClickListener itemViewOnClick() {
            return a->{
                if(bufferedSetsActivity.getSelectedId() == -1){
                    bufferedSetsActivity.setSelectedId(dto.getId());
                }
                else if(bufferedSetsActivity.getSelectedId() == dto.getId()){
                    bufferedSetsActivity.setSelectedId(-1);
                }
                else{
                    bufferedSetsActivity.setSelectedId(dto.getId());
                }
                bufferedSetsActivity.updateResultList();
            };
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private View.OnClickListener dateTimeOnClick() {
            return a->{
                getDateTime();
            };
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void getDateTime(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(itemView.getContext());
            datePickerDialog.setOnDateSetListener(onDateSet());
            datePickerDialog.show();
        }

        private DatePickerDialog.OnDateSetListener onDateSet() {
            return new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    newDay = datePicker.getDayOfMonth();
                    newMonth = datePicker.getMonth();
                    newYear = datePicker.getYear();
                    TimePickerDialog timePickerDialog = new TimePickerDialog(itemView.getContext(), onTimeSetListener(),currentHour, currentMinute,true);
                    timePickerDialog.show();
                }

                private TimePickerDialog.OnTimeSetListener onTimeSetListener() {
                    return new TimePickerDialog.OnTimeSetListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            newHour = timePicker.getHour();
                            newMinute = timePicker.getMinute();
                            saveNewTime();
                        }
                    };
                };
            };
        }

        private void saveNewTime() {
            dto.setTime(timeToMillis());
            bufferedSetsActivity.getBufferedSetService().update(dto);
            bufferedSetsActivity.updateResultList();
        }

        public void setDateTime(long timeMillis) {
            Date d = new Date(timeMillis);
            currentHour = d.getHours();
            currentMinute = d.getMinutes();
            dateTimeTextView.setText(d.toString());
        }

        public long timeToMillis(){
            Date d = new Date();
            d.setYear(newYear);
            d.setMonth(newMonth);
            d.setDate(newDay);
            d.setHours(newHour);
            d.setMinutes(newMinute);
            return d.getTime();
        }

        public void setColour(int selectedColour) {
            itemView.setBackgroundColor(selectedColour);
        }
    }

    private String getExerciseNameFromDB(int exerciseId){
        return bufferedSetsActivity.getExerciseService().findById(exerciseId).getName();
    }
}
