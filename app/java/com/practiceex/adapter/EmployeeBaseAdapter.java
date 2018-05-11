package com.practiceex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.practiceex.R;
import com.practiceex.model.Employee;

import java.util.ArrayList;

/**
 * Created by Pratyu on 12/10/2017.
 */

public class EmployeeBaseAdapter extends BaseAdapter {

    Context context;
    private LayoutInflater inflater;
    private ArrayList<Employee> arrayList;
    Employee tempValues = null;

    private OnImageClickListener listener;

    public EmployeeBaseAdapter(Context context, ArrayList<Employee> employeeArrayList) {
        this.context = context;
        this.arrayList = employeeArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void updateData(ArrayList<Employee> data){
        arrayList = data;
        this.notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView emp_name;
        ImageView edit_icon;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder;
        if (rowView == null){
            rowView = inflater.inflate(R.layout.emp_listview_row,viewGroup,false);

            viewHolder = new ViewHolder();
            viewHolder.emp_name = (TextView) rowView.findViewById(R.id.employee_name);
            viewHolder.edit_icon = (ImageView) rowView.findViewById(R.id.edit_icon);

            rowView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) rowView.getTag();
        if (arrayList.size() <= 0){

        }else{
            tempValues = null;
            tempValues = arrayList.get(position);

            final int index = position;
            viewHolder.emp_name.setText(tempValues.getFirst_name() + " " + tempValues.getLast_name());
            viewHolder.edit_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditClickListener(index);
                }
            });
        }
        return rowView;
    }

    public interface OnImageClickListener{
        public void onEditClickListener(int position);
    }
}
