package com.test.baseshop.fragment_profile.orders;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Objects;

public class fragment_orders_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;
    private int USER_ID;
    private Context context;

    fragment_orders_presenter(fragment_orders view){
        this.view = view;
        this.model = new fragment_orders_model(this);
        this.context = view.getContext();
        SharedPreferences sh = Objects.requireNonNull(view.getContext()).getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }


    @Override
    public void getData() {
        model.getOrdersByID(USER_ID);
    }

    @Override
    public void setAdapter(HashMap<String, Object> orders) {
        view.hideProgressBar();
        if(orders.size() == 0) view.showMessageEmptyOrders();
        else {
            RecyclerViewAdapterOrders adapter = new RecyclerViewAdapterOrders(context, orders);
            view.setAdapter(adapter);
        }
    }
}
