package edu.ar.unq.qubobe.data;

import edu.ar.unq.qubobe.itemtorepair.model.Article;
import edu.ar.unq.qubobe.itemtorepair.persistence.ArticleRegisters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    private final ArticleRegisters articleRegisters;

    @Autowired
    public DataLoader(ArticleRegisters articleRegisters) {
        this.articleRegisters = articleRegisters;
    }

    @PostConstruct
    private void loadData() {
        loadArticlesData();
    }

    private void loadArticlesData() {
        addArticle("CELULAR", "Samsung", "Galaxy S2");
        addArticle("CELULAR", "Samsung", "Galaxy S3");
        addArticle("NOTEBOOK", "Asus", "UX 410");
        addArticle("NOTEBOOK", "Mac", "Air");
        addArticle("TABLET", "Xiaomi", "Plus");
        addArticle("TABLET", "Xiaomi", "Plus X5");
    }

    private void addArticle(String name, String brand, String model) {
        articleRegisters.add(Article.named(name, brand, model));
    }
}