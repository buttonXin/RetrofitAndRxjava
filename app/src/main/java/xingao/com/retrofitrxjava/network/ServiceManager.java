package xingao.com.retrofitrxjava.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xingao.com.retrofitrxjava.AppUrl;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class ServiceManager {

    public APIService mAPIService;

    private static ServiceManager mServiceManager = null ;

    private ServiceManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mAPIService = retrofit.create(APIService.class);
    }

    public static ServiceManager getManager(){
        if (mServiceManager == null){
            mServiceManager = new ServiceManager();
        }
        return mServiceManager ;
    }
}
