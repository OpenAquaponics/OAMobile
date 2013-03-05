
package com.androidhive.jsonparsing;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragmentC extends Fragment {

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  View myFragmentView = inflater.inflate(R.layout.fragment_c, container, false);
  return myFragmentView;
 }

}