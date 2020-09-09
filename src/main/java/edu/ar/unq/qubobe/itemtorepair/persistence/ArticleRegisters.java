package edu.ar.unq.qubobe.itemtorepair.persistence;

import ar.com.kfgodel.nary.api.optionals.Optional;
import edu.ar.unq.qubobe.itemtorepair.model.Article;

public interface ArticleRegisters {
    Optional<Article> getByName(String name);

    void add(Article article);
}
