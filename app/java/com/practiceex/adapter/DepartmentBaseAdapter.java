package com.practiceex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.practiceex.ui.DashboardActivity;
import com.practiceex.R;
import com.practiceex.model.Departments;

import java.util.List;

/**
 * Created by Pratyu on 12/4/2017.
 */

public class DepartmentBaseAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<Departments> arrayList;
    Departments tempValues = null;

    public DepartmentBaseAdapter(Context context, List<Departments> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
    public long getItemId(int position) {
        return position;
    }

    public void updateData(List<Departments> departments){
        arrayList = departments;
        this.notifyDataSetChanged();
    }

    public static class ViewHolder{
        public TextView department_name;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder holder;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.depts_listview_row, viewGroup, false);

            holder = new ViewHolder();
            holder.department_name = (TextView) rowView.findViewById(R.id.department_name);

            rowView.setTag(holder);
        }else{
            rowView = view;
        }

        holder = (ViewHolder) rowView.getTag();

        if (arrayList.size() <= 0){
            holder.department_name.setText("NA");
        }else{
            tempValues = null;
            tempValues = arrayList.get(position);

            String dept_name = "NA";
            if(tempValues.getDept_name() != null && !tempValues.getDept_name().equals("")){
                dept_name = tempValues.getDept_name();
            }

            holder.department_name.setText(dept_name);
        }
        return rowView;
    }

}
