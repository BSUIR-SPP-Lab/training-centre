package com.bsuir.trainingcenter.service.impl;

public interface BiProcedure<T,K> {
    void accept(T t, K k);
}
