package com.bitoola.apps.mobile.android.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.Fragment;

import com.bitoola.apps.mobile.android.activity.base.SingleFragmentDrawerActivity;
import com.bitoola.apps.mobile.android.fragment.SearchableFragment;
import com.bitoola.apps.mobile.android.provider.DefaultSearchRecentSuggestionsProvider;

public class SearchableActivity extends SingleFragmentDrawerActivity {

	String query;
	
	@Override
	protected void onCreate(Bundle state) {
		
		handleIntent(getIntent());
		
		super.onCreate(state);
	} 
	
	public void onNewIntent(Intent intent) { 

		setIntent(intent); 
      	handleIntent(intent); 
	} 
	
	@Override
	protected Fragment getDefaultFragment() {
		
		return SearchableFragment.newIntance(query); 
	}
	
	private void handleIntent(Intent intent) {
		
		if( ! Intent.ACTION_SEARCH.equals(intent.getAction())) {
			query = null;
			return;
		}
		
		query = intent.getStringExtra(SearchManager.QUERY);
		
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                DefaultSearchRecentSuggestionsProvider.AUTHORITY, DefaultSearchRecentSuggestionsProvider.MODE);
        
        suggestions.saveRecentQuery(query, null);
	}
}
