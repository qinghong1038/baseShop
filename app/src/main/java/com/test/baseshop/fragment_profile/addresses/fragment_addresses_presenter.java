package com.test.baseshop.fragment_profile.addresses;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.baseshop.R;
import com.test.baseshop.fragment_profile.addresses.add_new.BottomSheetAddNewAddress;
import com.test.baseshop.model_helper.Address;

import java.util.List;
import java.util.Objects;

public class fragment_addresses_presenter implements Interfaces.Presenter{
    private Interfaces.View view;
    private Interfaces.Model model;
    private int USER_ID;
    private LayoutInflater inflater;
    private ViewGroup parent;

    fragment_addresses_presenter(fragment_addresses view){
        this.view = view;
        this.model = new fragment_addresses_model(this);
        this.inflater = view.getLayoutInflater();
        SharedPreferences sh = Objects.requireNonNull(view.getContext()).getSharedPreferences("AUTH_PREF", Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }


    @Override
    public void getAddresses(ViewGroup parent) {
        this.parent = parent;
        model.getAddresses(USER_ID);
//        for(Address address:addresses){
//            View address_view = inflater.inflate(R.layout.fragment_profile_fragment_addresses_base_address_cell,parent,false);
//            TextView title = address_view.findViewById(R.id.title_user);
//            TextView district = address_view.findViewById(R.id.district_user);
//            TextView street = address_view.findViewById(R.id.street_user);
//            TextView house = address_view.findViewById(R.id.house_user);
//            TextView corpus = address_view.findViewById(R.id.corpus_user);
//            TextView porch = address_view.findViewById(R.id.porch_user);
//            TextView floor = address_view.findViewById(R.id.floor_user);
//            TextView flat = address_view.findViewById(R.id.flat_user);
//
//            title.setText(address.getTitle());
//            district.setText(address.getDistrict());
//            street.setText(address.getStreet());
//            house.setText(address.getHouse());
//            corpus.setText(address.getCorpus());
//            porch.setText(address.getPorch());
//            floor.setText(address.getFloor());
//            flat.setText(address.getFlat());
//
//            view.addNewCellOfAddressView(address_view);
//        }
//        View address_view_add = inflater.inflate(R.layout.fragment_profile_fragment_addresses_plus,parent,false);
//        address_view_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                view.showBottomSheetAddNewAddress(BottomSheetAddNewAddress.newInstance());
//            }
//        });
//        view.addNewCellOfAddressView(address_view_add);

    }

    @Override
    public void setAddresses(List<Address> addresses) {
        view.hideProgressBar();
        Log.d("ADDRESSES",addresses.size()+"");
        for(Address address:addresses){
            View address_view = inflater.inflate(R.layout.fragment_profile_fragment_addresses_base_address_cell,parent,false);
            TextView title = address_view.findViewById(R.id.title_user);
            TextView district = address_view.findViewById(R.id.district_user);
            TextView street = address_view.findViewById(R.id.street_user);
            TextView house = address_view.findViewById(R.id.house_user);
            TextView corpus = address_view.findViewById(R.id.corpus_user);
            TextView porch = address_view.findViewById(R.id.porch_user);
            TextView floor = address_view.findViewById(R.id.floor_user);
            TextView flat = address_view.findViewById(R.id.flat_user);

            title.setText(address.getTitle());
            district.setText(address.getDistrict());
            street.setText(address.getStreet());
            house.setText(address.getHouse());
            corpus.setText(address.getCorpus());
            porch.setText(address.getPorch());
            floor.setText(address.getFloor());
            flat.setText(address.getFlat());

            view.addNewCellOfAddressView(address_view);
        }
    }

    @Override
    public void onButtonNewAddressClick() {
        view.showBottomSheetAddNewAddress(BottomSheetAddNewAddress.newInstance());
    }
}
