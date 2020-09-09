package edu.ar.unq.qubobe.itemtorepair.persistence;

import edu.ar.unq.qubobe.itemtorepair.Article;

public interface ArticleRegisters {
    Article getByName(String name);
}
