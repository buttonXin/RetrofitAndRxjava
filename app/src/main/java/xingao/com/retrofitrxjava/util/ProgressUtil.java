package xingao.com.retrofitrxjava.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class ProgressUtil {

    private static ProgressDialog dialog;
    /*
    * 开始显示dialog
    * */
    public static void showDialog(Context context){
        dialog = new ProgressDialog(context);
        dialog.setTitle("标题");
        dialog.setMessage("wait...");
        dialog.show();
    }

    /*
    * 关闭dialog
    * */
    public static void dismissDialog(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
