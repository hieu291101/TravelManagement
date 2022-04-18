package com.lth.service.impl;

import com.lth.pojos.News;
import com.lth.repository.NewsRepository;
import com.lth.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository  newsRepository;

    @Override
    public List<News> getNews(String keyword, int page) {
        return newsRepository.getNews(keyword, page);
    }

    @Override
    public long countNews() {
        return 0;
    }

    @Override
    public boolean addNews(News news) {
        return newsRepository.addNews(news);
    }

    @Override
    public boolean updateNews(News news) {
        return newsRepository.updateNews(news);
    }

    @Override
    public boolean deleteNews(News news) {
        return newsRepository.deleteNews(news);
    }
}
