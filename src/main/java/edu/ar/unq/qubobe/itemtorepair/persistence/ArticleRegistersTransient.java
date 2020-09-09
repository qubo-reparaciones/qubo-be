package edu.ar.unq.qubobe.itemtorepair.persistence;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.itemtorepair.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRegistersTransient implements ArticleRegisters {
    private List<Article> articles;

    public ArticleRegistersTransient() {
        articles = new ArrayList<>();
    }

    @Override
    public Optional<Article> getByName(String name) {
        return Optional.create(articles.stream().filter(article -> article.isNamed(name)).findFirst());
    }

    @Override
    public void add(Article article) {
        articles.add(article);
    }
}
