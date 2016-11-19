package xingao.com.retrofitrxjava;

import android.app.Application;

import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
    }
}
