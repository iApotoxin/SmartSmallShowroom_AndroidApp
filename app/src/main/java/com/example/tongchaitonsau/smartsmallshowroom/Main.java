package com.example.tongchaitonsau.smartsmallshowroom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Main.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main extends Fragment{
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private ViewStub stubGrid;
    private List<Product> productList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Main() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main.
     */
    // TODO: Rename and change types and number of parameters
    public static Main newInstance(String param1, String param2) {
        Main fragment = new Main();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        stubGrid = (ViewStub) view.findViewById (R.id.stub_grid);
        stubGrid.inflate();
        gridView = (GridView) view.findViewById(R.id.my_grid);

        gridView.setOnItemClickListener(onitemclick);
        stubGrid.setVisibility(View.VISIBLE);


        getProductList();
        gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item, productList);

        gridView.setAdapter(gridViewAdapter);
        return view;
    }
    public List<Product> getProductList(){
        productList = new ArrayList<>();

        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_1","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_2","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_3","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_4","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_5","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_6","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_7","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_8","","200"));
        productList.add(new Product(R.drawable.ic_music_note_black_24dp,"Item_9","","200"));

        return productList;
    }

    AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent goPurchase = new Intent(getActivity(),PurchaseActivity.class);
            goPurchase.putExtra("PASS_NAME",productList.get(i).getName());
            startActivity(goPurchase);


        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
