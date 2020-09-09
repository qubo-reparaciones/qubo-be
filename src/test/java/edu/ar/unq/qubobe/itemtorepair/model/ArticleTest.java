package edu.ar.unq.qubobe.itemtorepair.model;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArticleTest {
    @Test
    void canNotCreateArticleWithEmptyName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("", "brand", "model"));
        assertThat(thrown.getMessage(), Matchers.is(Article.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateCustomerWithNoneName() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named(null, "brand", "model"));
        assertThat(thrown.getMessage(), is(Article.NAME_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateArticleWithEmptyBrand() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("name", "", "model"));
        assertThat(thrown.getMessage(), Matchers.is(Article.BRAND_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateArticleWithNoneBrand() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("name", null, "model"));
        assertThat(thrown.getMessage(), Matchers.is(Article.BRAND_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateArticleWithEmptyModel() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("name", "brand", ""));
        assertThat(thrown.getMessage(), Matchers.is(Article.MODEL_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateArticleWithNoneModel() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("name", "brand", null));
        assertThat(thrown.getMessage(), Matchers.is(Article.MODEL_CAN_NOT_BE_EMPTY));
    }

    @Test
    void canCreateArticleWithNoneModel() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> Article.named("name", "brand", null));
        assertThat(thrown.getMessage(), Matchers.is(Article.MODEL_CAN_NOT_BE_EMPTY));
    }

    @Test
    void whenCreateAValidArticleHasTheValuesThatReceived() {
        Article article = Article.named("name", "brand", "model");
        assertThat(article.getName(), is("name"));
        assertThat(article.getBrand(), is("brand"));
        assertThat(article.getModel(), is("model"));
    }
}