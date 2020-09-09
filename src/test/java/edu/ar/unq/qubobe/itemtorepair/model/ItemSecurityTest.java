package edu.ar.unq.qubobe.itemtorepair.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemSecurityTest {
    @Test
    void whenCreateAValidItemToRepairHasTheValuesThatReceived() {
        ItemSecurity security = ItemSecurity.create("PIN", "1A2B");
        assertThat(security.getType(), is("PIN"));
        assertThat(security.getSecurity(), is("1A2B"));
    }

    @Test
    void canNotCreateItemSecurityWithNoneType() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemSecurity.create(null, "security"));
        assertThat(thrown.getMessage(), is(ItemSecurity.TYPE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemSecurityWithEmtpyType() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemSecurity.create("", "security"));
        assertThat(thrown.getMessage(), is(ItemSecurity.TYPE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemSecurityWithNoneSecurity() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemSecurity.create("type", null));
        assertThat(thrown.getMessage(), is(ItemSecurity.SECURITY_VALUE_NOT_BE_EMPTY));
    }

    @Test
    void canNotCreateItemSecurityWithEmptySecurity() {
        RuntimeException thrown = assertThrows(RuntimeException.class,
            () -> ItemSecurity.create("type", ""));
        assertThat(thrown.getMessage(), is(ItemSecurity.SECURITY_VALUE_NOT_BE_EMPTY));
    }
}