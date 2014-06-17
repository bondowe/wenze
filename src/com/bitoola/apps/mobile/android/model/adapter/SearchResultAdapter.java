package com.bitoola.apps.mobile.android.model.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitoola.apps.mobile.android.R;
import com.bitoola.apps.mobile.android.model.SearchResult;

public class SearchResultAdapter extends GenericAdapter<SearchResult> {

	public SearchResultAdapter(Context context, List<SearchResult> objects) {
		super(context, R.layout.list_item_search_result, objects);
	}

	@Override
	public void bindItem(int position, View convertView, ViewGroup parent) {
		
		TextView titleTextView = (TextView)convertView.findViewById(R.id.search_result_title);
		TextView subTitleTextView = (TextView)convertView.findViewById(R.id.search_result_subtitle);
		
		SearchResult searchResult = getItem(position);
		
		titleTextView.setText(searchResult.getTitle());
		subTitleTextView.setText(searchResult.getSubtitle());
		
		// ...
	}

}
