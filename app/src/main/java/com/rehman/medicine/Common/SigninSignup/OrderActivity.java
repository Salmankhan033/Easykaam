package com.rehman.medicine.Common.SigninSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.rehman.medicine.Adaptor.ProductsAdaptor;
import com.rehman.medicine.HelperClasses.OrderHelperClass;
import com.rehman.medicine.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<OrderHelperClass> myOrderList;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    OrderHelperClass order;
String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recyclerView=findViewById(R.id.recylerView);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
      userId= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView .setLayoutManager(gridLayoutManager);
        myOrderList=new ArrayList<>();

        final ProductsAdaptor.MyAdapter myAdapter=new ProductsAdaptor.MyAdapter(OrderActivity.this,myOrderList);
        recyclerView.setAdapter(myAdapter);

        databaseReference.child("Orders").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // order=snapshot.getValue(OrderHelperClass.class);
                myOrderList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    order=itemSnapshot.getValue(OrderHelperClass.class);
                    order.setOrderId(itemSnapshot.getKey());
                    myOrderList.add(order);


                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

}