package com.example.accountbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 旭东 on 2017/3/13.
 */

public class CostListAdapter extends BaseAdapter {

    private List<CostBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CostListAdapter(Context context,List<CostBean> list){
        this.mContext=context;
        this.mList=list;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.list_item,null);
            viewHolder.mCostTitle=(TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.mCostData=(TextView)convertView.findViewById(R.id.tv_data);
            viewHolder.mCostMoney=(TextView)convertView.findViewById(R.id.tv_cost);
            convertView.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        CostBean bean=mList.get(position);
        viewHolder.mCostTitle.setText(bean.costTitle);
        viewHolder.mCostData.setText(bean.costData);
        viewHolder.mCostMoney.setText(bean.costMoney);
        return convertView;
    }

    //ViewHolder内部类
    private static class ViewHolder{
        public TextView mCostTitle;
        public TextView mCostData;
        public TextView mCostMoney;
    }

}
