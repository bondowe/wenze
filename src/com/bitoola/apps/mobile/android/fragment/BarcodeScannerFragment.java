package com.bitoola.apps.mobile.android.fragment;

import java.io.IOException;
import java.util.List;

import com.bitoola.apps.mobile.android.R;

import android.annotation.TargetApi;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BarcodeScannerFragment extends Fragment {
	
	private static final String LOG_TAG = BarcodeScannerFragment.class.getName();
	
	private Camera mCamera;
	private SurfaceView mSurfaceView;
	private Button mBarcodeScannerButton;
	private View mProgressContainerView;
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onResume() {
		super.onResume();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			mCamera = Camera.open(0);
		} else {
			mCamera = Camera.open();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_barcode_scanner, null);
		
		mSurfaceView = (SurfaceView)v.findViewById(R.id.barcode_scanner_surface_view);
		
		setUpSurfaceHolder();
		
		mProgressContainerView = v.findViewById(R.id.progress_container_view);
		
		mProgressContainerView.setVisibility(View.INVISIBLE);
		
		mBarcodeScannerButton = (Button)v.findViewById(R.id.barcode_scan_button);
		
		mBarcodeScannerButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});
		
		return v;
	}
	
	public static BarcodeScannerFragment newInstance() {
		
		BarcodeScannerFragment fragment = new BarcodeScannerFragment();
		
		return fragment;
	}
	
	@SuppressWarnings("deprecation")
	private void setUpSurfaceHolder() {
		
		SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		surfaceHolder.addCallback(new SurfaceHolderCallback());
	}
	
	private void takePicture() {
		
		ShutterCallback shutterCallback = new ShutterCallback() {
			
			@Override
			public void onShutter() {
				mProgressContainerView.setVisibility(View.VISIBLE);
			}
		};
		
		PictureCallback pictureCallback = new PictureCallback() {
			
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				
				
				mProgressContainerView.setVisibility(View.INVISIBLE);
				mCamera.startPreview();
			}
		};
		
		mCamera.takePicture(shutterCallback, null, pictureCallback);
	}
	
	private Size getBestSupportedSize(List<Size> sizes, int width, int height) {
		
		Size bestSize = null;
		int largestArea = 0;
		int area = 0;
		
		for (Size size : sizes) {
			if(size.width > width || size.height > height) {
				continue;
			}
			
			area = size.width * size.height;
			
			if (largestArea < area) {
				bestSize = size;
				largestArea = area;
			}
		}
		
		if(bestSize == null) {
			bestSize = sizes.get(0);
		}
		
		return bestSize;
	}
	
	private class SurfaceHolderCallback implements SurfaceHolder.Callback {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				if(mCamera != null) {
					mCamera.setPreviewDisplay(holder);
				}
			} catch(IOException e) {
				Log.e(LOG_TAG, "Failed to set the preview display", e);
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
			if(mCamera == null) {
				return;
			}
			
			Camera.Parameters parameters = mCamera.getParameters();
			
			Size size = getBestSupportedSize(parameters.getSupportedPreviewSizes(), width, height);
			parameters.setPreviewSize(size.width, size.height);
			
			size = getBestSupportedSize(parameters.getSupportedPictureSizes(), width, height);
			parameters.setPictureSize(size.width, size.height);
			
			mCamera.setParameters(parameters);
			
			try {
				mCamera.setDisplayOrientation(90);
				mCamera.startPreview();
			} catch (Exception e) {
				Log.e(LOG_TAG, "Could not start preview", e);
				mCamera.release();
				mCamera = null;
			}
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			if (mCamera != null) {
				mCamera.stopPreview();
			}
		}
	}
}