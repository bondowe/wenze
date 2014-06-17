package com.bitoola.apps.mobile.android.provider;

import android.content.SearchRecentSuggestionsProvider;

public class DefaultSearchRecentSuggestionsProvider extends
		SearchRecentSuggestionsProvider {

	public static final String AUTHORITY = "com.bitoola.apps.mobile.android.provider.DefaultSearchRecentSuggestionsProvider";
	public static final int MODE = DATABASE_MODE_QUERIES;
	
	public DefaultSearchRecentSuggestionsProvider() {
		setupSuggestions(AUTHORITY, MODE);
	}
}
