package org.itstep.sport.service.service;

public interface CabinetService<T> {

    T getUserCabinet(String username);

    T update(T t, String username);
}
