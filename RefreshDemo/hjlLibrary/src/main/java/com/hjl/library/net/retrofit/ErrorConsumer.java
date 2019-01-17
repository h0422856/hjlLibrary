package com.hjl.library.net.retrofit;

/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-04-20]
 */
public interface ErrorConsumer<T> {
    T onError(Throwable throwable);
}
