package com.test.baseshop.fragment_menu;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapter adapter);
        void updateRecyclerView();
        void setSections(int[] sections_codes);
        void showMinusIconAndNumberOfItemForOrder(int position);
        void hideMinusIconAndNumberOfItemForOrder(int position);
        void setNumberOfItemForOrder(int position, int number_of_item_for_order);

    }

    interface Presenter{
        void getData(Context context, int code);
        void getSections();
        void OnSectionItemClick(android.view.View v);
        String getTitleOfSectionByCode(int code);



        interface ConnectionBetweenViewAndRecyclerList {
            void tellViewToShowMinusIconAndNumberOfItemForOrder(int position);
            void tellViewToHideMinusIconAndNumberOfItemForOrder(int position);
            void tellViewToSetNumberOfItemForOrder(int position, int number_of_item_for_order);
        }

        interface ConnectionBetweenModelAndRecyclerList{
            void tellModelToSetNewNumberOfItemsForOrder(int item_id, int new_count_of_items_for_order);
            HashMap<Integer,Integer> tellModelToGetBasketOfItemsForOrder();
        }
    }

    interface Model{
        List<Item> getItemsByFilter(int code);
        int[] getSections();

        interface Basket{
            HashMap<Integer,Integer> getBasketForUser(int user_id);
            void sendNewNumberOfItemsForOrder(int user_id, int item_id, int count_of_items_for_order);
        }

        interface Photo{
            void setImageInBackground(Item item);
            }
    }

}
