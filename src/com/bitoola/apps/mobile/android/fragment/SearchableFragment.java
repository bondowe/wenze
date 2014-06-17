package com.bitoola.apps.mobile.android.fragment;

import java.util.ArrayList;
import java.util.List;

import com.bitoola.apps.mobile.android.R;
import com.bitoola.apps.mobile.android.model.SearchResult;
import com.bitoola.apps.mobile.android.model.adapter.SearchResultAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class SearchableFragment extends ListFragment {
	
	private static final String EXTRA_QUERY = "com.bitoola.apps.mobile.android.fragment.QUERY";
	
	private String query; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle args = getArguments();
		query = args.getString(EXTRA_QUERY);
		
		List<SearchResult> searchResult = getSearchResult(query);
		
		ListAdapter listAdapter = new SearchResultAdapter(getActivity(), searchResult);
		setListAdapter(listAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_searchable, null);
	}
	
	public static Fragment newIntance(String query) {
		
		Bundle args = new Bundle();
		args.putString(EXTRA_QUERY, query);
		
		Fragment fragment = new SearchableFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	private List<SearchResult> getSearchResult(String query2) {
		
		List<SearchResult> result = new ArrayList<>();	
		return result;
	}
}
