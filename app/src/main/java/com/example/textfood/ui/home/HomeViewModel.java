package com.example.textfood.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.textfood.Callback.IBestDealCallBackListener;
import com.example.textfood.Callback.IPopularCallBackListener;
import com.example.textfood.Common.Common;
import com.example.textfood.Model.BestDealModel;
import com.example.textfood.Model.PopularCategoryModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements IPopularCallBackListener, IBestDealCallBackListener {

private MutableLiveData<List<PopularCategoryModel>> popularList;
    private MutableLiveData<List<BestDealModel>> bestDealList;
private MutableLiveData<String> messageError;
private IPopularCallBackListener popularCallBackListener;
private IBestDealCallBackListener bestDealCallBackListener;


    public HomeViewModel() {
    popularCallBackListener = this;
    bestDealCallBackListener = this;
    }

    public MutableLiveData<List<BestDealModel>> getBestDealList() {
        if(bestDealList == null){
            bestDealList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadBestDealList();
        }
        return bestDealList;
    }

    private void loadBestDealList() {
        List<BestDealModel> tempList = new ArrayList<>();
        DatabaseReference bestDealRef = FirebaseDatabase.getInstance().getReference(Common.BEST_DEALS_REF);
        bestDealRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                   BestDealModel model = itemSnapshot.getValue(BestDealModel.class);
                   tempList.add(model);
               }
               bestDealCallBackListener.onBestDealLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             bestDealCallBackListener.onBestDealLoadFailed(error.getMessage());
            }
        });
    }

    public MutableLiveData<List<PopularCategoryModel>> getPopularList() {
        if(popularList == null){
            popularList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadPopularList();
        }
        return popularList;
    }

    private void loadPopularList() {
        List<PopularCategoryModel> tempList = new ArrayList<>();
        DatabaseReference popularRef = FirebaseDatabase.getInstance().getReference(Common.POPULAR_CATEGORY_REF);
        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                 PopularCategoryModel model = itemSnapshot.getValue(PopularCategoryModel.class);
                 tempList.add(model);
             }
             popularCallBackListener.onPopularLoadSuccess(tempList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                 popularCallBackListener.onPopularLoadFailed(error.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModels) {
     popularList.setValue(popularCategoryModels);
    }

    @Override
    public void onPopularLoadFailed(String message) {
        messageError.setValue(message);
    }

    @Override
    public void onBestDealLoadSuccess(List<BestDealModel> bestDealModels) {
        bestDealList.setValue(bestDealModels);
    }

    @Override
    public void onBestDealLoadFailed(String message) {
         messageError.setValue(message);
    }
}