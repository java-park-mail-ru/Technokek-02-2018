package main.service;

import java.util.List;

public class Paginator<T> {

    public List<T> paginate(Integer page, List<T> array) {

        Integer numOfPages = array.size() / 10 + 1;
        if (page < 1) {
            page = 1;
        }

        if (page > numOfPages) {
            page = numOfPages;
        }

        return array.subList((page - 1) * 10, page * 10);

    }
}
