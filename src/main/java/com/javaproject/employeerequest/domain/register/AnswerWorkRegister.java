package com.javaproject.employeerequest.domain.register;

import java.util.ArrayList;
import java.util.List;

public class AnswerWorkRegister {
    private List<AnswerWorkRegisterItem> items;

    public void addItem(AnswerWorkRegisterItem item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public List<AnswerWorkRegisterItem> getItems() {
        return items;
    }

}
