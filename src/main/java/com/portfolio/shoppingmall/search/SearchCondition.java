package com.portfolio.shoppingmall.search;

import lombok.Data;

@Data
public class SearchCondition {
    String content;
    SearchType type;

    public SearchCondition(String content, SearchType type) {
        this.content = content;
        this.type = type;
    }

    public SearchCondition(String content) {
        this.content = content;
    }
}
