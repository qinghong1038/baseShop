package com.test.baseshop.fragment_basket;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.baseshop.fragment_basket.offer_order.BottomSheetOfferOrder;
import com.test.baseshop.model_helper.Item;

import java.util.List;
import java.util.Objects;

public class fragment_basket_presenter implements Interfaces.Presenter,Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList,Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList {

    private com.test.baseshop.fragment_basket.Interfaces.View view;
    private com.test.baseshop.fragment_basket.Interfaces.Model.Basket model;
    private int USER_ID;
    private Context context;

    fragment_basket_presenter(fragment_basket view){
        this.context = view.getContext();
        this.view = view;
        this.model = new fragment_basket_model(this);
        SharedPreferences sh = Objects.requireNonNull(view.getContext()).getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }


    @Override
    public void OnOrderButtonClick(int size_of_adapter_items) {
        if(size_of_adapter_items > 0) {
            view.showOfferOfOrder(BottomSheetOfferOrder.newInstance());
//            model.sendThatUserWantToMakeOrder(USER_ID);
//            view.clearBasket();
//            view.updateRecycleView();
        }else {
            String message_error = "Но вы же не можете заказать ничего";
            view.showError(message_error);
        }
    }

    @Override
    public void getDataOfBasketInfo() {
        model.getDataOFBasketInfoRemote(USER_ID);
//        RecyclerViewAdapterBasket adapter = new RecyclerViewAdapterBasket(context,list_of_items,(Interfaces.Model.Photo) model,this);
//        view.setAdapter(adapter);
//        view.updateRecycleView();
    }

    @Override
    public void setDataOfBasket(List<Item> items) {
        view.hideProgressBar();

        RecyclerViewAdapterBasket adapter = new RecyclerViewAdapterBasket(context,items,(Interfaces.Model.Photo) model,this);
        view.setAdapter(adapter);
    }

    @Override
    public void tellModelToDownloadImageOfItemAndSetToImageViewByItem(Item item) {
        ((Interfaces.Model.Photo) model).setImageWithPicasso(item);
    }

    @Override
    public void tellModelToSetNewNumberOfItemsForOrder(int item_id, int new_count_of_items_for_order) {
        model.sendNewNumberOfItemsForOrder(USER_ID,item_id,new_count_of_items_for_order);
    }


    @Override
    public void tellViewToRemoveItemForOrder(int position) {
        view.removeItemForOrder(position);
//        view.updateRecycleView();
    }

    @Override
    public void tellViewWhatDatasetUpdated() {
        view.updateRecycleView();
    }

    @Override
    public void tellViewToSetNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(int position, int number_of_item_for_order, int price) {
        view.setNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(position, number_of_item_for_order, price);
    }

    @Override
    public void tellViewToShowLLContainerWithDesc(int position) {
        view.showDescOfItemForOrder(position);
    }

    @Override
    public void tellViewToHideLLContainerWithDesc(int position) {
        view.hideDescOfItemForOrder(position);
    }

    @Override
    public void tellViewToSetTotalSummOfItemsForOrder(int new_total_summ) {
        view.setNewTotalSummOfItemsForOrder(new_total_summ);
    }
}
