package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hook {
        @Before
        public void before() {
            System.out.println("Тесты запущены!\n");
        }

        @After
        public void after() {
            System.out.println("Тесты завершены!\n");
        }
}
