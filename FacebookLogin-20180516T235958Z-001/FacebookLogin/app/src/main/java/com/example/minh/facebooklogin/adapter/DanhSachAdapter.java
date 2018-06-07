package com.example.minh.facebooklogin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.minh.facebooklogin.R;
import com.example.minh.facebooklogin.model.ChiTietBD;
import com.example.minh.facebooklogin.model.Xe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.minh.facebooklogin.R.id.txttenxe;


/**
 * Created by LaVanDuc on 5/17/2018.
 */

public class DanhSachAdapter extends BaseExpandableListAdapter {
    Context myContext;
    ArrayList<Xe> listdataHeader;
    HashMap<Xe, List<ChiTietBD>> listdataChild;

    public DanhSachAdapter(Context myContext, ArrayList<Xe> listdataHeader, HashMap<Xe, List<ChiTietBD>> listdataChild) {
        this.myContext = myContext;
        this.listdataHeader = listdataHeader;
        this.listdataChild = listdataChild;
    }

    @Override
    public int getGroupCount() {
        return listdataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listdataChild.get(listdataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listdataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listdataChild.get(listdataHeader.get(groupPosition)).get(childPosition);
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

        Xe titleHeader= (Xe) getGroup(groupPosition);
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_item_header,null);
        TextView txtheader=convertView.findViewById(txttenxe);
        txtheader.setText(titleHeader.getTenxe());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChiTietBD ct= (ChiTietBD) getChild(groupPosition,childPosition);


        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_item_child,null);


        TextView txtten=convertView.findViewById(R.id.txttenpt);
        TextView txtcachthuc=convertView.findViewById(R.id.txtcachthuc);
        TextView txtngay=convertView.findViewById(R.id.txtngaybd);
        TextView txtghichu=convertView.findViewById(R.id.txtghichu);

        txtten.setText(ct.getTenpt());
        txtcachthuc.setText("Cách thức: "+ct.getCachthuc());
        txtngay.setText("Ngày: "+ct.getNgay());
        txtghichu.setText("Ghi chú: "+ct.getGhichu());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {


        return true;
    }
}
