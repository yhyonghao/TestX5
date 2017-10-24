package com.example.pc.testx5;

import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by llbt on 2016/4/25.
 */
public class ToastUtil {

    public static void show(Object object) {
        Toast toast = Toast.makeText(BaseApplication.getInstance(), object + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
