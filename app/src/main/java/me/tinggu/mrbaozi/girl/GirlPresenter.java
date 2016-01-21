package me.tinggu.mrbaozi.girl;

import com.cyou.quick.mvp.rx.scheduler.MvpRxPresenter;

import java.util.List;

import me.tinggu.common.utils.LogUtils;
import me.tinggu.common.rest.RestUtils;
import me.tinggu.model.PrettyGirl;
import me.tinggu.mrbaozi.api.GirlApi;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/19 11:15
 */
public class GirlPresenter extends MvpRxPresenter<GirlView, List<PrettyGirl>> {
    private GirlApi girlApi;

    public GirlPresenter() {
        girlApi = RestUtils.createApi(GirlApi.class);
    }

    public void fetchGirlData(boolean pullToRefresh) {
        subscribe(girlApi.fetchPrettyGirl(null));
    }


    @Override
    protected void onNext(List<PrettyGirl> lists) {
        LogUtils.i("baozi", "list size = " + lists.size());
        LogUtils.i("baozi", "0 item = " + lists.get(0));
        getView().setData(lists);
    }

    @Override
    protected void onError(Throwable throwable) {
        getView().showError(throwable, false);
    }

    @Override
    protected void onCompleted() {
        LogUtils.i("baozi", "onCompleted");
    }
}
