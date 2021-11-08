package com.rehman.medicine.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rehman.medicine.Common.ProductDetailActivity;
import com.rehman.medicine.HelperClasses.OrderHelperClass;
import com.rehman.medicine.Model.ProductsModel;
import com.rehman.medicine.R;

import java.util.ArrayList;
import java.util.List;



public class ProductsAdaptor extends RecyclerView.Adapter<ProductsAdaptor.ProductView> {
List<ProductsModel> productsModelsList;


    public ProductsAdaptor(List<ProductsModel> productsModelsList) {
        this.productsModelsList=productsModelsList;
    }

    public ProductsAdaptor(FirebaseRecyclerOptions<ProductsModel> options) {

    }


    @Override
    public ProductsAdaptor.ProductView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ProductView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdaptor.ProductView holder, int position) {
        String product_Image =productsModelsList.get(position).getImage();
        String product_name=productsModelsList.get(position).getPname();
        String product_price=productsModelsList.get(position).getPrice();
        String product_descrption=productsModelsList.get(position).getDescription();
        String shop_name=productsModelsList.get(position).getShopeName();
        String product_id=productsModelsList.get(position).getShop_Id();
        String product_date=productsModelsList.get(position).getDate();
        String product_address=productsModelsList.get(position).getAddress();

        String shopId=productsModelsList.get(position).getShop_Id();


        holder.setProducData(product_Image,product_name,product_price,product_address);
        holder.detailActivity(product_name,product_price,shop_name,product_descrption,product_Image,product_date,position,product_address ,shopId);


    }

    @Override
    public int getItemCount() {
        return productsModelsList.size();
    }

    public void filterdList(ArrayList<ProductsModel> filterList) {
        productsModelsList=filterList;
        notifyDataSetChanged();
    }


    public class ProductView extends RecyclerView.ViewHolder{
        ImageView productPic;
         TextView addToCart,pName,pDiscription,pPrice,pAddress;
       //  CardView cardView;


        public ProductView(@NonNull View itemView) {
            super(itemView);
            pName=itemView.findViewById(R.id.product_name);
            pAddress=itemView.findViewById(R.id.product_Address);
            pPrice=itemView.findViewById(R.id.product_price);
            productPic=itemView.findViewById(R.id.product_image);

//            cardView=itemView.findViewById(R.id.card);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                }
//            });
        }

        private void setProducData(String iconUrl,String PName,String PPrice ,String PAddress) {
            Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.logo)).into(productPic);
              pName.setText("Product Name: "+PName);
            // pDiscription.setText("Discription: "+PDiscription);
             pPrice.setText("RS."+PPrice);
              pAddress.setText("Shop Address:  "+PAddress);
        }
        private void detailActivity(final String name, final String price, final String shopName, final String description, final String pImage, final String time, final int position, final String shop_address,final String shopId) {



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), ProductDetailActivity.class);


//                    Bitmap b=pName.getDrawingCache();
//
//
//                    intent.putExtra("Bitmap", b);

                    intent.putExtra("pName", name);
                    intent.putExtra("pPrice", price);
                    intent.putExtra("shopName", shopName);
                    intent.putExtra("pDescription", description);
                  //  intent.putExtra("pImage", productsModelsList.get(position).getImage());
                    intent.putExtra("pImage",pImage);
                    intent.putExtra("pTime", time);
                    intent.putExtra("shopId", shopId);
                    intent.putExtra("pAddress", shop_address);
                    itemView.getContext().startActivity(intent);

                }
            });

        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.OrderView> {
        private Context myContext;
        private List<OrderHelperClass> myOrderList;
        String Key;


        public MyAdapter(Context myContext, List<OrderHelperClass> myOrderList) {
            this.myContext = myContext;
            this.myOrderList = myOrderList;
        }

        @NonNull
        @Override
        public OrderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card, parent, false);
            return new OrderView(mView);
        }

        @Override
        public void onBindViewHolder(@NonNull final OrderView holder, final int position) {
            holder.CName.setText(myOrderList.get(position).getCustomer_Name());
            holder.CPhone.setText(myOrderList.get(position).getCustomer_No());
            holder.CAddress.setText(myOrderList.get(position).getCustomer_Address());
            holder.CRequirents.setText(myOrderList.get(position).getProduct_Name());
            holder.CFeedback.setText(myOrderList.get(position).getCustomer_feedback() );
            holder.pack.setText(myOrderList.get(position).getPack() );

               final String id= FirebaseAuth.getInstance().getUid().toString();

            holder.AcceptOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mediaPlayer = MediaPlayer.create(myContext, R.raw.click_sound);
                    mediaPlayer.start();
                    Key = myOrderList.get(position).getOrderId();
                    String pack=myOrderList.get(position).getPack();
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(id);
                    databaseReference.child(Key).removeValue();
                    Toast.makeText(myContext, "Order Accepted", Toast.LENGTH_LONG).show();

                }
            });
            holder.Call_Customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final MediaPlayer mediaPlayer = MediaPlayer.create(myContext, R.raw.click_sound);
                    mediaPlayer.start();
                    String mobileNo = myOrderList.get(position).getCustomer_No();
    //                Toast.makeText(v.getContext(),"NO;"+mobileNo,Toast.LENGTH_LONG).show();
                    String call = "tel:" + mobileNo.trim();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(call));
                    myContext.startActivity(intent);


                }
            });
        }


        @Override
        public int getItemCount() {
            return myOrderList.size();

        }


        public class OrderView extends RecyclerView.ViewHolder {


            private TextView CName, CPhone, CAddress, CRequirents,CFeedback,pack;
            CardView cardView;
            Button AcceptOrder, Call_Customer;


            public OrderView(View itemView) {
                super(itemView);
                CName = itemView.findViewById(R.id.customerName);
                CPhone = itemView.findViewById(R.id.customerPhone);
                CAddress = itemView.findViewById(R.id.customerAddress);
                CRequirents = itemView.findViewById(R.id.customerRequrements);
                cardView = itemView.findViewById(R.id.myCard);
                AcceptOrder = itemView.findViewById(R.id.acceptOrder);
                Call_Customer = itemView.findViewById(R.id.callCustomer);
                CFeedback=itemView.findViewById(R.id.customerfdbk);
                pack=itemView.findViewById(R.id.pack);


            }
        }
    }
}
