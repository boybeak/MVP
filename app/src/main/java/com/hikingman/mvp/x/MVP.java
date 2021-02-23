package com.hikingman.mvp.x;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hikingman.mvp.v4.IPresenter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MVP {
    public static final String TAG = MVP.class.getSimpleName();
    private static final MVP mvp = new MVP();
    public static MVP getInstance() {
        return mvp;
    }
    private final Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            Log.v(TAG, "onActivityCreated " + System.currentTimeMillis());
            if (activity instanceof BaseMVPActivity) {
                ParameterizedType baseMVPActivity = (ParameterizedType)activity.getClass().getGenericSuperclass();
                Class<?> presenterType = (Class<?>) baseMVPActivity.getActualTypeArguments()[0];
                try {
                    IPresenter presenter = (IPresenter) presenterType.newInstance();
                    ((BaseMVPActivity) activity).setPresenter(presenter);
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
        }
    };
    private MVP(){}
    public void install(Application app) {
        app.registerActivityLifecycleCallbacks(callbacks);
    }
}
