package com.example.cristiane_aula.prjlanchonete.util;

/**
 * Created by RAFAEL on 18/03/2017.
 */

public interface Func<T,V> {
    T call(V v) throws  Exception;
}
