package Exam;

import Exam.models.FavoriteRecord;
import Exam.models.User;

import java.util.Comparator;
import java.util.List;

public class UserComparator implements Comparator<User> {
    static FileManager fileManager = new FileManager();
    static List<FavoriteRecord> records = fileManager.getEntitiesFromFile("favorites.tsv", FavoriteRecord.class);

    private int getRecordCountsForUser(String userId) {
        return (int) records.stream().filter(record -> record.getUserId().equals(userId)).count();
    }

    @Override
    public int compare(User o1, User o2) {
        return getRecordCountsForUser(o1.getId()) - getRecordCountsForUser(o2.getId());
    }
}
