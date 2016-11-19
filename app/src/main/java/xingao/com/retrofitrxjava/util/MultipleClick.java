package xingao.com.retrofitrxjava.util;

import android.os.Handler;
import android.view.View;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class MultipleClick {
    /**
     * 防止多次点击 在点击事件里面调用
     * */
    public static void blockView(final View view) {
        view.setEnabled(false);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 500);
    }
}
