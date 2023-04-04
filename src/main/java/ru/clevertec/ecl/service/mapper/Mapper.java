package ru.clevertec.ecl.service.mapper;

public interface Mapper<F, T> {

    T mapFrom(F obj);
}
