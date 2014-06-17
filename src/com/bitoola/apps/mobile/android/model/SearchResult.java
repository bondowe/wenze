package com.bitoola.apps.mobile.android.model;

import java.net.URI;

public class SearchResult {

	private String mTitle;
	private String mSubtitle;
	private String mDescription;
	private  URI mImageURI;
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public String getSubtitle() {
		return mSubtitle;
	}
	public void setSubtitle(String subtitle) {
		mSubtitle = subtitle;
	}
	public String getDescription() {
		return mDescription;
	}
	public void setDescription(String description) {
		mDescription = description;
	}
	public URI getImageURI() {
		return mImageURI;
	}
	public void setImageURI(URI imageURI) {
		mImageURI = imageURI;
	}
}
