package com.rehman.medicine.shopkeeper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rehman.medicine.R;
import com.rehman.medicine.ServiceProviders.ServiceProviderDashboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UploadProductsActivity extends AppCompatActivity {


    private String CategoryNam, Description, Price, Pname, saveCurrentDate, quantity, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice,InputProductQnt;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;
    String shopName;
    String location;
    String shopId;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_products);





        shopId= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseDatabase.child(shopId).child("fullName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shopName=snapshot.getValue(String.class);
             //   Toast.makeText(getApplicationContext(),"Shop: /t" +shopName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        firebaseDatabase.child(shopId).child("address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                location=snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









     //   CategoryName = Objects.requireNonNull (Objects.requireNonNull (getIntent ().getExtras ()).get ("category")).toString ();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance ().getReference ().child ("Products");


        AddNewProductButton = findViewById (R.id.add_new_product);
        InputProductImage = findViewById(R.id.select_product_image);
        InputProductName = findViewById(R.id.product_name);
        InputProductDescription = findViewById(R.id.product_description);
        InputProductPrice = findViewById(R.id.product_price);
        InputProductQnt=findViewById(R.id.product_qnt);

        loadingBar = new ProgressDialog(this);


        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {  final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click_sound);
                mediaPlayer.start();
                OpenGallery();
            }
        });


        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click_sound);
                mediaPlayer.start();
                ValidateProductData();
            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData()
    {
        Description = InputProductDescription.getText().toString();
        Price = InputProductPrice.getText().toString();
        Pname = InputProductName.getText().toString();
        quantity=InputProductQnt.getText().toString();



        if (ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }



    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(UploadProductsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(UploadProductsActivity.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(UploadProductsActivity.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfoToDatabase()
    {



        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("description", Description);
        productMap.put("image", downloadImageUrl);
        productMap.put("price", Price);
        productMap.put("pname", Pname);
        productMap.put("address",location );
        productMap.put("shopeName",shopName );
        productMap.put("Quantity",quantity );
        productMap.put("Shop_Id",shopId);
//           ProductsRef.child(shopName).setValue(productMap)
        ProductsRef.child("Shop").child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(UploadProductsActivity.this, ServiceProviderDashboard.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(UploadProductsActivity.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UploadProductsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),ServiceProviderDashboard.class);
        startActivity(intent);
    }

}
