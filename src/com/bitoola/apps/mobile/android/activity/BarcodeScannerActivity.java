package com.bitoola.apps.mobile.android.activity;

import android.support.v4.app.Fragment;
import com.bitoola.apps.mobile.android.activity.base.SingleFragmentDrawerActivity;
import com.bitoola.apps.mobile.android.fragment.BarcodeScannerFragment;

public class BarcodeScannerActivity extends SingleFragmentDrawerActivity {
	
	@Override
	protected Fragment getDefaultFragment() {
		
		return BarcodeScannerFragment.newInstance();
	}
}
