package com.example.textfood.Callback;

import com.example.textfood.Model.PopularCategoryModel;

import java.util.List;

public interface IPopularCallBackListener {
    void onPopularLoadSuccess(List<PopularCategoryModel> popularCategoryModels);
    void onPopularLoadFailed(String message);
}
