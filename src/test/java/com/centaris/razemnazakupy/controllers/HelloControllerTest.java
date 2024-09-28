package com.centaris.razemnazakupy.controllers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {
    private final HelloController helloController = new HelloController();

    @Test
    void shouldReturnHello() {
        assertThat(helloController.hello()).isEqualTo("hello");
    }
}