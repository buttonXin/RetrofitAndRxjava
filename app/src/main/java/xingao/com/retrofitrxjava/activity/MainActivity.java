package xingao.com.retrofitrxjava.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xingao.com.retrofitrxjava.AppUrl;
import xingao.com.retrofitrxjava.R;
import xingao.com.retrofitrxjava.model.NewsContent;
import xingao.com.retrofitrxjava.model.NewsLatest;
import xingao.com.retrofitrxjava.model.StratImage;
import xingao.com.retrofitrxjava.network.APIService;
import xingao.com.retrofitrxjava.network.ServiceManager;
import xingao.com.retrofitrxjava.util.ACache;
import xingao.com.retrofitrxjava.util.MultipleClick;
import xingao.com.retrofitrxjava.util.ProgressUtil;

public class MainActivity extends AppCompatActivity {


    private TextView mTextView;
    private ImageView mImageView;
    private Button mButton;
    private Subscription mSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // doStartImg();

        // loadContent();



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultipleClick.blockView(mButton);//调用防止多次点击
                loadContentRX();
            }
        });
    }

    private void loadContentRX() {
        ProgressUtil.showDialog(MainActivity.this);//显示dialog

        //添加防抖操作，多次点击
//这个方法
//因为没有api就用知乎日报的，return 里面写的内容就是再次进行了网路请求
// .subscribeOn(Schedulers.newThread())
//在io里执行缓存操作 ， 在主线程将缓存的内容取出来！！可以这么做
//                .doOnNext(new Action1<NewsContent>() {
//                    @Override
//                    public void call(NewsContent newsContent) {
//                        ACache.get(MainActivity.this).put("news" , newsContent);
//                    }
//                })
//取消
//取消
//  Logger.d("000"+ ACache.get(MainActivity.this).getAsObject("news").toString());
//取消
        mSubscribe = ServiceManager.getManager().mAPIService
                .loadNewsLatsetRX("latest")
                .throttleFirst(5000, TimeUnit.MINUTES)//添加防抖操作，多次点击
                //这个方法
                .flatMap(new Func1<NewsLatest, Observable<NewsContent>>() {
                    @Override
                    public Observable<NewsContent> call(NewsLatest newsLatest) {
                        int id = newsLatest.getStories().get(0).getId();
                        //因为没有api就用知乎日报的，return 里面写的内容就是再次进行了网路请求
                        return ServiceManager.getManager().mAPIService.loadNewContentRX(id);
                    }
                })
                // .subscribeOn(Schedulers.newThread())
                //在io里执行缓存操作 ， 在主线程将缓存的内容取出来！！可以这么做
                .subscribeOn(Schedulers.io())
//                .doOnNext(new Action1<NewsContent>() {
//                    @Override
//                    public void call(NewsContent newsContent) {
//                        ACache.get(MainActivity.this).put("news" , newsContent);
//                    }
//                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsContent>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("成功");
                        ProgressUtil.dismissDialog();//取消
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        ProgressUtil.dismissDialog();//取消
                    }

                    @Override
                    public void onNext(NewsContent newsContent) {
                        Logger.d(newsContent.toString());

                        //  Logger.d("000"+ ACache.get(MainActivity.this).getAsObject("news").toString());
                        ProgressUtil.dismissDialog();//取消
                    }
                });



    }

    @Override
    protected void onPause() {
        super.onPause();
        mSubscribe.unsubscribe();
        Logger.d("zhixing");
    }

    private void loadContent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        apiService.loadNewsContent(3892357)
                .enqueue(new Callback<NewsContent>() {
                    @Override
                    public void onResponse(Call<NewsContent> call, Response<NewsContent> response) {
                        NewsContent newsContent = response.body();
                        Logger.d(newsContent.toString());

                    }

                    @Override
                    public void onFailure(Call<NewsContent> call, Throwable t) {

                    }
                });
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.image_start);
        mTextView = (TextView) findViewById(R.id.text_start_des);
        mButton = (Button) findViewById(R.id.btn_http);
    }

    /**
     * 下载启动图片
     * */
    public void doStartImg(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        apiService.loadStartImage().enqueue(new Callback<StratImage>() {
            @Override
            public void onResponse(Call<StratImage> call, Response<StratImage> response) {
                Logger.d(response.body().getText());
                StratImage stratImage = response.body();

                mTextView.setText(stratImage.getText());
                Glide.with(MainActivity.this).load(stratImage.getImg()).into(mImageView);
            }

            @Override
            public void onFailure(Call<StratImage> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
