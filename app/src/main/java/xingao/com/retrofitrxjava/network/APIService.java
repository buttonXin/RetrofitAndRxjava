package xingao.com.retrofitrxjava.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import xingao.com.retrofitrxjava.model.NewsContent;
import xingao.com.retrofitrxjava.model.NewsLatest;
import xingao.com.retrofitrxjava.model.StratImage;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public interface APIService {

    /**
     * 尽量不要在前面加  /   ，尽量在BASE_URL后面加  /  ， 然后组成的就是   base_url + start-image/1080*1776
     * */
    @GET("start-image/1080*1776")
    Call<StratImage> loadStartImage();

    @GET("news/{id}")
    Call<NewsContent> loadNewsContent(@Path("id") int id);

    @GET("news/{id}")
    Observable<NewsContent> loadNewContentRX(@Path("id") int id);

    @GET("news/{latest}")
    Observable<NewsLatest>  loadNewsLatsetRX(@Path("latest") String latset);
}