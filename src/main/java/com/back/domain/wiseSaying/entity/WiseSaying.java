package com.back.domain.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class WiseSaying {
    static int lastQuoteNo = 0;
    int quoteId;
    @Setter
    String quote;
    @Setter
    String author;

    public WiseSaying(String quote, String author) {
        this.quoteId = ++lastQuoteNo;
        this.quote = quote;
        this.author = author.isEmpty() ? "입력없음": author;
    }

    public static int getLastQuoteNo() { return lastQuoteNo;}

    public boolean compareNo(int i) {return (quoteId == i);}

    public String toString() {return quoteId + " / " + author + " / " + quote;}

    public static void resetLastQuoteNoForTest() {lastQuoteNo = 0;}
}