package com.bsuir.trainingcenter.dao.Impl.Helpers;

import java.util.List;
import java.util.Optional;

public class ListHelper {
    public static <T> Optional<T> getFirst(List<T> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0));
        }
    }
}
