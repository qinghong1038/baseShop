package com.test.baseshop.fragment_basket.offer_order;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.fragment_basket.fragment_basket;

import java.util.Objects;


public class BottomSheetOfferOrder extends BottomSheetDialogFragment implements Interfaces.View{

    private bsv_offer_order_presenter presenter;

    public static String TAG = "OfferOrder";

    public static BottomSheetOfferOrder newInstance() {
        return new BottomSheetOfferOrder();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_basket_bsv_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPresenter();
        presenter.getAddresses(getLayoutInflater(), (ViewGroup) view.findViewById(R.id.fragment_basket_bsv_container_addresses));

        initOclToButtonForOrder();
        initOclToButtonsPlusAndMinusPersons();

    }

    private void initPresenter(){
        presenter = new bsv_offer_order_presenter(this);
    }

    private void initOclToButtonForOrder(){
        Button button_for_order = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_button_for_order);
        final EditText desc = getView().findViewById(R.id.fragment_basket_bsv_desc_et);
        button_for_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.OnButtonOrderClick(desc.getText().toString());
            }
        });
    }

    private void initOclToButtonsPlusAndMinusPersons(){
        ImageView plus = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_persons_plus);
        ImageView minus = getView().findViewById(R.id.fragment_basket_bsv_persons_minus);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.fragment_basket_bsv_persons_plus) presenter.OnButtonPersonsPlusClick();
                else presenter.OnButtonPersonsMinusClick();
            }
        };
        plus.setOnClickListener(ocl);
        minus.setOnClickListener(ocl);
    }


    @Override
    public void showErrorEmptyAddress() {
        Toast.makeText(getContext(),"Необходимо выбрать адрес",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorStrange() {
        Toast.makeText(getContext(),"Произошла непредвиденная ошибка",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorZeroAddresses() {
        Toast.makeText(getContext(), "У Вас нет адресов", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUserWantsZeroPersons() {
        Toast.makeText(getContext(),"Вы точно не робот?",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorUserWantsUpperThanMaxPersons() {
        Toast.makeText(getContext(), "Достигнуто максимальное количество персон", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void clearBasket() {
        ((fragment_basket) Objects.requireNonNull(getParentFragment())).clearBasket();
        ((fragment_basket) getParentFragment()).updateRecycleView();
    }

    @Override
    public void showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection() {
        Toast.makeText(getContext(),"Скоро Вам позвонят.\nСтатус заказа можете посмотреть во вкладке Профиль\n Спасибо за заказ!\n",Toast.LENGTH_LONG).show();
    }

    @Override
    public void addNewAddressCellInContainer(View v) {
        LinearLayout container_addresses = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_container_addresses);
        container_addresses.addView(v);
    }

    @Override
    public void setButtonForOrderDisabled() {
        Button button_for_order = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_button_for_order);
        button_for_order.setEnabled(false);
    }

    @Override
    public void dismissBSV() {
        this.dismiss();
    }

    @Override
    public void showNewPersonsNumber(int persons) {
        TextView et_persons = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_persons);
        et_persons.setText(String.valueOf(persons));
    }

    @Override
    public void hideProgressBar() {
        View container = getView();
        if(container != null) {
            ProgressBar progressBar_in_container_addresses = container.findViewById(R.id.fragment_basket_bsv_container_addresses_progressBar);
            progressBar_in_container_addresses.setVisibility(View.GONE);
        }
    }
}
