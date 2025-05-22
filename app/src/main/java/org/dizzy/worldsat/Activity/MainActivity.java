package org.dizzy.worldsat.Activity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.dizzy.worldsat.Adapter.CategoryAdapter;
import org.dizzy.worldsat.Adapter.PopularAdapter;
import org.dizzy.worldsat.Adapter.RecommendedAdapter;
import org.dizzy.worldsat.Adapter.SliderAdapter;
import org.dizzy.worldsat.Domain.Category;
import org.dizzy.worldsat.Domain.ItemDomain;
import org.dizzy.worldsat.Domain.Location;
import org.dizzy.worldsat.Domain.SliderItems;
import org.dizzy.worldsat.R;
import org.dizzy.worldsat.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BasicActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        
        intiLocation();

        initBanners();

        initCategory();

        initRecommended();

        initPopular();



    } //onCreate()


    private void initCategory() {

        DatabaseReference ref = database.getReference("Category"); //create reference of the database

        binding.pbCategoty.setVisibility(View.VISIBLE);


        ArrayList<Category> listCate = new ArrayList<Category>(); //create the bottle i will take the data into it

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        listCate.add(issue.getValue(Category.class));
                    }

                    if (!listCate.isEmpty()){

                        binding.rvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.HORIZONTAL , false));
                        RecyclerView.Adapter adapter = new CategoryAdapter(listCate);
                        binding.rvCategory.setAdapter(adapter);
                    }

                    binding.pbCategoty.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void intiLocation() {

         DatabaseReference ref = database.getReference("Location"); //create reference of the database


         ArrayList<Location> listLoc = new ArrayList<Location>(); //create the bottle i will take the data into it

         ref.addListenerForSingleValueEvent(new ValueEventListener() {

             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {

                 if(snapshot.exists()){
                     for(DataSnapshot issue:snapshot.getChildren()){
                         Location location = issue.getValue(Location.class); //casting the return value to Location object
                         listLoc.add(location);
                     }

                     ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item , listLoc);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     binding.spinner.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });



    } //initLocation


    private void banners ( ArrayList<SliderItems> items ){

        binding.vpSlider.setAdapter(new SliderAdapter(items , binding.vpSlider));
        binding.vpSlider.setClipToPadding(false);
        binding.vpSlider.setClipChildren(false);
        binding.vpSlider.setOffscreenPageLimit(3);
        binding.vpSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer CPT = new CompositePageTransformer();

        CPT.addTransformer(new MarginPageTransformer(40));

        binding.vpSlider.setPageTransformer(CPT);


    } // bannerMethod

    private  void initBanners(){

        DatabaseReference ref = database.getReference("Banner");


        ArrayList<SliderItems> items = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        items.add(issue.getValue(SliderItems.class));
                    }
                }
                banners(items);
                binding.pbBanner.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    } //initBanners

    private void initRecommended() {

        DatabaseReference myref = database.getReference("Item");

        ArrayList<ItemDomain> items = new ArrayList<>();

        binding.pbRecommended.setVisibility(View.VISIBLE);

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        items.add(issue.getValue(ItemDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.rvRecommended.setLayoutManager(new LinearLayoutManager(MainActivity.this ,LinearLayoutManager.HORIZONTAL , false ));
                        RecyclerView.Adapter adapter = new RecommendedAdapter(items);
                        binding.rvRecommended.setAdapter(adapter);
                    }
                    binding.pbRecommended.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initPopular() {

        DatabaseReference myref = database.getReference("Popular");

        ArrayList<ItemDomain> items = new ArrayList<>();

        binding.pbPopular.setVisibility(View.VISIBLE);

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        items.add(issue.getValue(ItemDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.rvPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this ,LinearLayoutManager.HORIZONTAL , false ));
                        RecyclerView.Adapter adapter = new PopularAdapter(items);
                        binding.rvPopular.setAdapter(adapter);
                    }
                    binding.pbPopular.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}