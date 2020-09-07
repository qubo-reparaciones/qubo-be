package edu.ar.unq.qubobe.itemtorepair;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemToRepairTest {
    // seguridad(pin, patron, contraseña)
    @Test
    void canNotCreateItemToRepairWithoutArticle() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.create(null, "nroserie"));
        assertThat(thrown.getMessage(), is(ItemToRepair.ARTICLE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemToRepairWithNoneSerialNumber() {
        Article article = Article.named("name", "brand", "model");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.create(article, null));
        assertThat(thrown.getMessage(), is(ItemToRepair.SERIAL_NUMBER_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemToRepairWithEmptySerialNumber() {
        Article article = Article.named("name", "brand", "model");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.create(article, ""));
        assertThat(thrown.getMessage(), is(ItemToRepair.SERIAL_NUMBER_NOT_BE_EMPTY));
    }

    @Test
    void whenCreateAValidItemToRepairHasTheValuesThatReceived() {
        Article article = Article.named("name", "brand", "model");
        ItemToRepair itemToRepair = ItemToRepair.create(article, "nroserie");
        assertThat(itemToRepair.getArticle(), is(article));
        assertThat(itemToRepair.getSerialNumber(), is("nroserie"));
    }
}