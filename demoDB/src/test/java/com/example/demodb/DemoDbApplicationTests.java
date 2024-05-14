package com.example.demodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoDbApplicationTests {
    @Autowired
    private MyMapper mmapper;

    @Test
    public void testDelete() {
        mmapper.delete(20);
    }

}
