package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
}
