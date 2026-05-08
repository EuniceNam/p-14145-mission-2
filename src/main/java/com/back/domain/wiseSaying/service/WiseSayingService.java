package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public int register(String quote, String author) {
        if (quote.isEmpty()) { return -1;}
        int lastQuoteNo;
        lastQuoteNo = wiseSayingRepository.create(quote, author);
        return lastQuoteNo;
    }
    public String readPage(int pageNo) {
        List<WiseSaying> page = wiseSayingRepository.fetchPage(pageNo);
        if (page.isEmpty()) { return "";}
        Collections.reverse(page);
        return page.stream().map(WiseSaying::toString)
                .collect(Collectors.joining("\n", "", "\n"));
    }

    public int getPageCount() {
        return wiseSayingRepository.getPageCount();
    }

    public String filter(String keywordType, String keyword) {
        List<WiseSaying> filteredPage = wiseSayingRepository.filter(keywordType, keyword);
        Collections.reverse(filteredPage);
        return filteredPage.stream().map(WiseSaying::toString)
                .collect(Collectors.joining("\n", "", "\n"));
    }

    public boolean delete(int qid) {
        return wiseSayingRepository.delete(qid); // 없으면 null 리턴
    }
    public String[] read(int qid) {
        return wiseSayingRepository.read(qid);
    }
    public void edit(int qid, String newQuote, String newAuthor) {
        if (newQuote.isEmpty()) { return;}
        wiseSayingRepository.update(qid, newQuote, newAuthor);
    }
}
