package ru.javawebinar.topjava.repository.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}
