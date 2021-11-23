package com.example.textfood.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.example.textfood.Adapter.MyBestDealsAdapter;
import com.example.textfood.Adapter.MyPopularCategoriesAdapter;
import com.example.textfood.R;
import com.example.textfood.databinding.FragmentHomeBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Unbinder unbinder;
@BindView(R.id.recycler_popular)
    RecyclerView recycler_popular;
@BindView(R.id.viewpager)
    LoopingViewPager viewpager;
LayoutAnimationController layoutAnimationController;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
             unbinder = ButterKnife.bind(this,root);
             init();
             // create adapter
             homeViewModel.getPopularList().observe(getViewLifecycleOwner(),popularCategoryModels -> {
                 MyPopularCategoriesAdapter adapter = new MyPopularCategoriesAdapter(getContext(),popularCategoryModels);
                 recycler_popular.setAdapter(adapter);
                 recycler_popular.setLayoutAnimation(layoutAnimationController);
             });
             homeViewModel.getBestDealList().observe(getViewLifecycleOwner(),bestDealModels -> {
                 MyBestDealsAdapter adapter = new MyBestDealsAdapter(getContext(),bestDealModels,true);
                 viewpager.setAdapter(adapter);
             });
        return root;
    }

    private void init() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_item_from_left);
        recycler_popular.setHasFixedSize(true);
        recycler_popular.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

    }

    @Override
    public void onResume() {
        super.onResume();
        viewpager.resumeAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewpager.pauseAutoScroll();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}