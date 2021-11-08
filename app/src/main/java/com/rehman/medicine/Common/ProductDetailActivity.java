package com.rehman.medicine.Common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rehman.medicine.HelperClasses.OrderHelperClass;
import com.rehman.medicine.HelperClasses.SinUpHelperClass;
import com.rehman.medicine.NavigationDrawer.NavigationDrawerActivity;
import com.rehman.medicine.R;


public class ProductDetailActivity extends AppCompatActivity {

    String pName,pPrice,pDescription,pTime,pShop,pImge,pAddress,shopId;
    TextView name,price,shop,time,description,address;
    ImageView pPic;
    EditText feedBack, pack;
    Button order;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SinUpHelperClass userData;
    String customer_name,customer_address,customer_No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        name=findViewById(R.id.Name);
        price=findViewById(R.id.Price);
        shop=findViewById(R.id.shop);
        time=findViewById(R.id.time);
        address=findViewById(R.id.address);
        description=findViewById(R.id.description);
        feedBack=findViewById(R.id.feeback);
        pack=findViewById(R.id.pack);
        order=findViewById(R.id.order);
        pPic=findViewById(R.id.product_pic);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();


        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

//        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress); // Android Android-SpinKit
//        Sprite foldingCube = new FoldingCube();                                 //https://github.com/ybq/Android-SpinKit
//        progressBar.setVisibility(View.VISIBLE);
//        progressBar.setIndeterminateDrawable(foldingCube);
// get data

        pName= getIntent().getStringExtra("pName");
        pPrice= getIntent().getStringExtra("pPrice");
        pShop= getIntent().getStringExtra("shopName");
        pDescription= getIntent().getStringExtra("pDescription");
        pImge= getIntent().getStringExtra("pImage");
        pTime= getIntent().getStringExtra("pTime");
        pAddress=getIntent().getStringExtra("pAddress");
        shopId=getIntent().getStringExtra("shopId");

//        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("Bitmap");
//        pPic.setImageBitmap(bitmap);

        name.setText(pName);
        price.setText("RS."+pPrice);
        shop.setText(pShop);
        time.setText(pTime);
        description.setText(pDescription);
        address.setText(pAddress);


       Glide.with(this).load(pImge).apply(new RequestOptions().placeholder(R.drawable.logo)).into(pPic);

        databaseReference.child("Users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userData=snapshot.getValue(SinUpHelperClass.class);
                customer_name=userData.getFullName();
                customer_address=userData.getAddress();
                customer_No=userData.getPhoneNo();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error: "+error.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
        //// reed user data
//        databaseReference.child("Customers").child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                userData=snapshot.getValue(SinUpHelperClass.class);
//                customer_name=userData.getFullName();
//                customer_address=userData.getAddress();
//                customer_No=userData.getPhoneNo();
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(),"Error: "+error.getMessage(),Toast.LENGTH_LONG).show();
//
//
//            }
//        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click_sound);
                mediaPlayer.start();



                // Toast.makeText(getApplicationContext(),"Your Order Place Succesfuly"+customer_address,Toast.LENGTH_LONG).show();

                String feedback=feedBack.getText().toString();
                String Pack=pack.getText().toString();
//               HashMap<String , Object> order=new HashMap<>();
//               order.put("Product_Name",pName);
//               order.put("Customer_Name",userData.getFullName());
//               order.put("Customer_Address",userData.getAddress());
//               order.put("Customer_No",userData.getPhoneNo());
//               order.put("Customer_feedback",feedback);
               if (TextUtils.isEmpty(Pack))
                {
                    Toast.makeText(getApplicationContext(), "Please write Medicine Pack", Toast.LENGTH_SHORT).show();
                }
               else if (TextUtils.isEmpty(feedback))
               {
                   Toast.makeText(getApplicationContext(), "Please write Feedback", Toast.LENGTH_SHORT).show();
               }
                else {
                   OrderHelperClass orderHelperClass = new OrderHelperClass(customer_name, customer_No, customer_address, pName, feedback, Pack,"");


                   databaseReference.child("Orders").child(shopId).push().setValue(orderHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(getApplicationContext(), "Your Order Place Succesfuly", Toast.LENGTH_LONG).show();
                               Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                               startActivity(intent);
                           } else {
                               String error = task.getException().getMessage();
                               Toast.makeText(getApplicationContext(), "Error :" + error, Toast.LENGTH_LONG).show();

                           }
                       }
                   }).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(getApplicationContext(), "Your Order Place", Toast.LENGTH_LONG).show();


                       }
                   });


               }
            }
        });
    }


}