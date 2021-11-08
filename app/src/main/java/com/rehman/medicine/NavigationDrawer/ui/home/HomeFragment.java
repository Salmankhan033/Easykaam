package com.rehman.medicine.NavigationDrawer.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rehman.medicine.Adaptor.ProductsAdaptor;
import com.rehman.medicine.Model.ProductsModel;
import com.rehman.medicine.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    String shop;

    RecyclerView productRecyclerView;
    private ProductsAdaptor productsAdaptor;
    List<ProductsModel> productsModelsList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    private HomeViewModel homeViewModel;
 EditText text_Search;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        text_Search=view.findViewById(R.id.txt_search);
        productRecyclerView = view.findViewById(R.id.p_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);



        productsModelsList = new ArrayList<ProductsModel>();
        productsAdaptor = new ProductsAdaptor(productsModelsList);
        productRecyclerView.setAdapter(productsAdaptor);

        databaseReference= FirebaseDatabase.getInstance().getReference("Products").child("Shop");
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //  productsModelsList.clear();
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    ProductsModel productsModel=itemSnapshot.getValue(ProductsModel.class);

                    productsModelsList.add(productsModel);
                }

                productsAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Error"+databaseError.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        text_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });




        return view;
    }

    private void filter(String text) {
        ArrayList<ProductsModel> filterList=new ArrayList<>();
        for(ProductsModel item: productsModelsList) {
            if ((item.getPname()).toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        productsAdaptor.filterdList(filterList);

    }


}