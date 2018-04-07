package main.service;

import java.util.List;

public class Paginator<T> {

    public List<T> paginate(Integer page, List<T> array) {

        Integer numOfPages = array.size() / 10;
        Integer remainder = array.size() % 10;

        if (remainder != 0) {
            numOfPages++;
        }

        if (page < 1) {
            page = 1;
        }

        if (numOfPages == 0) {
            numOfPages = 1;
        }

        if (page > numOfPages) {
            page = numOfPages;
        }


        if (page * 10 >= array.size()) {
            return array.subList((page - 1) * 10, (array.size() > 0) ? array.size() : 0);
        } else {
            return array.subList((page - 1) * 10, page * 10);
        }

    }
}
