package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingRepository {
    private final ArrayList<WiseSaying> wiseSayings = new ArrayList<>();
    private final int PAGE_SIZE = 5;

    public WiseSayingRepository() {}
    public int create(String quote, String author) {
        wiseSayings.add(new WiseSaying(quote, author));
        return WiseSaying.getLastQuoteNo();
    }

    public List<WiseSaying> fetchPage(int page) {
        int num = wiseSayings.size();
        if (num == 0) { return new ArrayList<>();}
        int end = num - (page - 1) * PAGE_SIZE; // 역순이므로
        int start = Math.max(end - PAGE_SIZE, 0);
        return new ArrayList<>(wiseSayings.subList(start, end));
    }
    public String[] read(int qid) {
        return wiseSayings.stream().filter(q -> q.compareNo(qid)).findFirst()
                .map(q -> new String[] {q.getQuote(), q.getAuthor()}).orElse(null);
    }
    public int getPageCount() {
        int totalQuotes = wiseSayings.size();
        return (totalQuotes + PAGE_SIZE - 1) / PAGE_SIZE;
    }
    public List<WiseSaying> filter(String keywordType, String keyword) {
        List<WiseSaying> filteredPage = wiseSayings.stream()
                .filter(q -> switch (keywordType) {
                    case "author" -> q.getAuthor().contains(keyword);
                    case "content" -> q.getQuote().contains(keyword);
                    default -> false;
                }).collect(Collectors.toList());
        int start = Math.max(0, filteredPage.size() - PAGE_SIZE);
        return new ArrayList<>(filteredPage.subList(start, filteredPage.size()));
    }
    public void update(int qid, String quote, String author) {
        wiseSayings.stream().filter(q -> q.compareNo(qid)).findFirst()
                .ifPresentOrElse(q -> { q.setQuote(quote); q.setAuthor(author);}, () -> {});
    }
    public boolean delete(int qid) {
        return wiseSayings.removeIf(q -> q.compareNo(qid));
    }
}
