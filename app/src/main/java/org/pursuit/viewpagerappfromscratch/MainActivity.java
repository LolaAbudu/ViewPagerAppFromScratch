package org.pursuit.viewpagerappfromscratch;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import org.pursuit.viewpagerappfromscratch.network.RetrofitSingleton;
import org.pursuit.viewpagerappfromscratch.network.ZodiacServices;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragmentList = new ArrayList<>();

        RetrofitSingleton.getInstance()
                .create(ZodiacServices.class)
                .getZodiac()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        zodiac -> {
                            Log.d("TAG", "onResponse " + zodiac.getZodiac().get(0).getName());
                            for (int i = 0; i < zodiac.getZodiac().size(); i--) {
                                fragmentList.add(ZodiacFragment.newInstance(zodiac.getZodiac().get(i).getName(),
                                        zodiac.getZodiac().get(i).getNumber(),
                                        zodiac.getZodiac().get(i).getImage()));

                                ViewPager viewPager = findViewById(R.id.view_pager);
                                viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
                            }
                        },
                        throwable -> Log.d("TAG", "onFailure " + throwable));
    }
}
