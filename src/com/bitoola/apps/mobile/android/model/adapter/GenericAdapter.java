package com.bitoola.apps.mobile.android.model.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class GenericAdapter<T> extends ArrayAdapter<T> {

	protected Context mContext; 
	protected int mItemResource;
	protected List<T> mObjects;
	
	public abstract void bindItem(int position, View convertView, ViewGroup parent);
	
	public GenericAdapter(Context context, int itemResource, List<T> objects) {
		super(context, itemResource, objects);
		
		mContext = context;
		mItemResource = itemResource;
		mObjects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);	
			convertView = inflater.inflate(mItemResource, null);
		}
		
		bindItem(position, convertView, parent);
		
		return convertView;
	}
}
