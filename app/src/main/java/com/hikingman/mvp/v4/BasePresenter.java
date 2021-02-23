package com.hikingman.mvp.v4;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V, M> {

    private static final String TAG = BasePresenter.class.getSimpleName();

    private WeakReference<V> viewReference;
    private V proxyView;
    private M m;

    @SuppressWarnings("unchecked")
    @Override
    public void attachView(V v) {
        viewReference = new WeakReference<>(v);
        proxyView = (V) Proxy.newProxyInstance(v.getClass().getClassLoader(), v.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.v(TAG, "run by proxy");
                        if (viewReference != null && viewReference.get() != null) {
                            return method.invoke(viewReference.get(), args);
                        }
                        return null;
                    }
                });

        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        if (type != null) {
            Type[] types = type.getActualTypeArguments();
            try {
                m = (M)((Class<?>)types[1]).newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void detachView() {
        viewReference.clear();
        viewReference = null;
    }

    @Override
    public V getView() {
        return proxyView;
    }

    protected M getModel() {
        return m;
    }
}
