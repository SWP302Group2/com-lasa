package com.lasa.data.validator.model;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidList<E> implements List<E> {
    @Delegate
    @Valid
    private List<E> list = new ArrayList<>();
}
