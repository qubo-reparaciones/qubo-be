package edu.ar.unq.qubobe.itemtorepair;

import ar.com.kfgodel.nary.api.optionals.Optional;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemToRepairTest {
    @Test
    void canNotCreateItemToRepairWithoutArticle() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.withoutSecurity(null, "nroserie"));
        assertThat(thrown.getMessage(), is(ItemToRepair.ARTICLE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemToRepairWithNoneSerialNumber() {
        Article article = Article.named("name", "brand", "model");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.withoutSecurity(article, null));
        assertThat(thrown.getMessage(), is(ItemToRepair.SERIAL_NUMBER_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemToRepairWithEmptySerialNumber() {
        Article article = Article.named("name", "brand", "model");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.withoutSecurity(article, ""));
        assertThat(thrown.getMessage(), is(ItemToRepair.SERIAL_NUMBER_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemToRepairWithNoneSecurity() {
        Article article = Article.named("name", "brand", "model");

        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemToRepair.withSecurity(article, "nroserie", null));
        assertThat(thrown.getMessage(), is(ItemToRepair.SECURITY_NOT_BE_EMPTY));
    }

    @Test
    void whenCreateAValidItemToRepairHasTheValuesThatReceived() {
        Article article = Article.named("name", "brand", "model");

        ItemToRepair itemToRepair = ItemToRepair.withoutSecurity(article, "nroserie");

        assertThat(itemToRepair.getArticle(), is(article));
        assertThat(itemToRepair.getSerialNumber(), is("nroserie"));
        assertThat(itemToRepair.getSecurity(), is(Optional.empty()));
    }

    @Test
    void whenCreateAValidItemToRepairWithSecurityHasTheSameValueInSecurity() {
        Article article = Article.named("name", "brand", "model");
        ItemSecurity security = ItemSecurity.create("PIN", "1A2B");

        ItemToRepair itemToRepair = ItemToRepair.withSecurity(article, "nroserie", security);

        assertThat(itemToRepair.getSecurity(), is(Optional.of(security)));
    }
}