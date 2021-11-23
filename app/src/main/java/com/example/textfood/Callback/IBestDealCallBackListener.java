package com.example.textfood.Callback;

import com.example.textfood.Model.BestDealModel;
import com.example.textfood.Model.PopularCategoryModel;

import java.util.List;

public interface IBestDealCallBackListener {
    void onBestDealLoadSuccess(List<BestDealModel> bestDealModels);
    void onBestDealLoadFailed(String message);
}
