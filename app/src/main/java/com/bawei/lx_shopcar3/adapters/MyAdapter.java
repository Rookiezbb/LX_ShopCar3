package com.bawei.lx_shopcar3.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bawei.lx_shopcar3.MainActivity;
import com.bawei.lx_shopcar3.R;
import com.bawei.lx_shopcar3.bean.MyBean;

import java.util.List;

/**
 * Created by Zhang on 2017/10/22.
 */

public class MyAdapter extends BaseExpandableListAdapter {

    private List<MyBean.DataBean> grouplist;
    private Context context;
    private MainActivity ma;
    public MyAdapter(List<MyBean.DataBean> grouplist, Context context) {
        this.grouplist = grouplist;
        this.context = context;
        ma = (MainActivity) context;
    }

    @Override
    public int getGroupCount() {
        return grouplist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return grouplist.get(groupPosition).getDatas().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return grouplist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return grouplist.get(groupPosition).getDatas().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final MyBean.DataBean dataBean = grouplist.get(groupPosition);
        convertView = View.inflate(context, R.layout.groupitem, null);
        CheckBox gck = (CheckBox) convertView.findViewById(R.id.group_ck);
        TextView gtv = (TextView) convertView.findViewById(R.id.group_tv);


        gtv.setText(dataBean.getTitle());

        gck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataBean.setCheck(isChecked);
                for (MyBean.DataBean.DatasBean dsb : dataBean.getDatas()) {
                    dsb.setCheck(isChecked);
                }
                if(isChecked){
                    ma.setCb(isAllChecked());
                }else{
                    ma.setCb(false);
                }
                notifyDataSetChanged();
            }
        });
        gck.setChecked(grouplist.get(groupPosition).isCheck());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final MyBean.DataBean.DatasBean datasBean = grouplist.get(groupPosition).getDatas().get(childPosition);
        convertView = View.inflate(context, R.layout.childitem, null);
        CheckBox cck = (CheckBox) convertView.findViewById(R.id.child_ck);
        TextView ctv = (TextView) convertView.findViewById(R.id.child_tv);
        TextView cpic = (TextView) convertView.findViewById(R.id.child_price);
        ctv.setText(grouplist.get(groupPosition).getDatas().get(childPosition).getType_name());
        cpic.setText(datasBean.getPrice()+" 元");

        cck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                datasBean.setCheck(isChecked);
                int num = 0 ;
                if(isChecked){

                }else{

                }
                for (MyBean.DataBean.DatasBean dsb : grouplist.get(groupPosition).getDatas()) {

                    if(!dsb.isCheck()) {

//                        MyBean.DataBean dab = grouplist.get(groupPosition);
//                        dab.setCheck(false);
                        num++;
                    }
//                    }else{
//                        MyBean.DataBean dab = grouplist.get(groupPosition);
//                        dab.setCheck(true);
//                    }

                }
               notifyDataSetChanged();

                if(num == 0){

                    MyBean.DataBean dab = grouplist.get(groupPosition);
                    dab.setCheck(true);
                }else{
                    MyBean.DataBean dab = grouplist.get(groupPosition);
                    dab.setCheck(false);
                }
            }
        });
        cck.setChecked(datasBean.isCheck());
        return convertView;
    }
    private boolean isAllChecked() {
        for (int i = 0; i < grouplist.size(); i++) {
            MyBean.DataBean dataBean = grouplist.get(i);
            if(!dataBean.isCheck()){
                return false;
            }
        }
        return true;
    }


    public void ckall(){
        for (int i = 0; i < grouplist.size(); i++) {
            MyBean.DataBean dataBean = grouplist.get(i);
            dataBean.setCheck(true);
            for (int j = 0; j < dataBean.getDatas().size(); j++) {
                MyBean.DataBean.DatasBean datasBean = dataBean.getDatas().get(j);
                datasBean.setCheck(true);
            }
        }
    }
    public void cknull(){
        for (int i = 0; i < grouplist.size(); i++) {
            MyBean.DataBean dataBean = grouplist.get(i);
            dataBean.setCheck(false);
            for (int j = 0; j < dataBean.getDatas().size(); j++) {
                MyBean.DataBean.DatasBean datasBean = dataBean.getDatas().get(j);
                datasBean.setCheck(false);
            }
        }
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
