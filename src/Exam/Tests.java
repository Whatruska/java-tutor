package Exam;

import Exam.models.FavoriteRecord;
import Exam.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Tests {

    private FileManager fileManager = new FileManager();
    private List<User> users = fileManager.getEntitiesFromFile("users.tsv", User.class);

    @Test
    public void compareTwoUsersWithOneSelectedItem() {
        UserComparator userComparator = new UserComparator();
        User artem = users.stream().filter(user -> user.getId().equals("1a23v")).findFirst().get();
        User alsu = users.stream().filter(user -> user.getId().equals("123saf@")).findFirst().get();
        Assert.assertEquals(userComparator.compare(alsu, artem), 0);
    }

    @Test
    public void testFirstTaskByFirstCity() {
        Set<Map.Entry<String, Long>> resultSet = Loader.getCityUserItemMap("Чистай");
        Assert.assertEquals(resultSet.size(), 1);
        Map.Entry<String, Long> entry = resultSet.stream().findFirst().get();
        Assert.assertEquals(entry.getKey(), "123saf@");
        Long value = entry.getValue();
        Assert.assertEquals(value, Long.valueOf("1"));
    }
}
