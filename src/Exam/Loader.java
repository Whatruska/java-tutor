package Exam;

import Exam.models.FavoriteRecord;
import Exam.models.Item;
import Exam.models.User;

import java.util.*;
import java.util.stream.Collectors;

public class Loader {
    static FileManager fileManager = new FileManager();
    static List<User> users = fileManager.getEntitiesFromFile("users.tsv", User.class);
    static List<Item> items = fileManager.getEntitiesFromFile("items.tsv", Item.class);
    static List<FavoriteRecord> records = fileManager.getEntitiesFromFile("favorites.tsv", FavoriteRecord.class);

    public static void main(String[] args) {
        Set<Map.Entry<String, Long>> set = getCityUserItemMap("Чистай");
        System.out.println(set);

        List<User> notPatrioticUsers = getNotPatrioticUsersList();
        System.out.println(notPatrioticUsers);

        List<User> youngUserWithOneProducerList = getYoungUserWithOneProducerList();
        System.out.println(youngUserWithOneProducerList.size() > 0);

        users.stream().sorted(new UserComparator()).forEach(System.out::println);
    }

    public static Set<Map.Entry<String, Long>> getCityUserItemMap (String city) {
        return users.stream()
                .filter(user -> user.getCity().equals(city))
                .collect(
                        Collectors.toMap(
                                User::getId,
                                user -> records.stream()
                                        .filter(
                                            record -> record.getUserId().equals(user.getId())
                                        )
                                        .count()
                        )
                ).entrySet();
    }

    public static List<User> getNotPatrioticUsersList () {
        List<User> notPatrioticUsers = new ArrayList<>();
        for (User user: users) {
            boolean isPatriotic = false;
            one: for (FavoriteRecord record: records) {
                if (record.getUserId().equals(user.getId())) {
                    for (Item item: items) {
                        if (item.getId().equals(record.getItemId()) && item.getProducerCity().equals(user.getCity())) {
                            isPatriotic = true;
                            break one;
                        }
                    }
                }
            }
            if (!isPatriotic) {
                notPatrioticUsers.add(user);
            }
        }
        return notPatrioticUsers;
    }

    public static List<User> getYoungUserWithOneProducerList () {
        List<User> youngUserWithOneProducerList = new ArrayList<>();
        users.stream().filter(user -> Integer.parseInt(user.getYear()) < 2003).forEach((user) -> {
            List<FavoriteRecord> userRecords = records.stream().filter(record -> record.getUserId().equals(user.getId())).collect(Collectors.toList());
            TreeMap<String, Set<String>> map = new TreeMap<>();
            userRecords.forEach(record -> {
                Set<String> currSet = map.get(record.getUserId());
                if (currSet == null) {
                    currSet = new TreeSet<>();
                }
                String producer = items.stream().filter(item -> item.getId().equals(record.getItemId())).findFirst().get().getProducer();
                currSet.add(producer);
                map.put(record.getUserId(), currSet);
            });
            map.entrySet().forEach(entry -> {
                if (entry.getValue().size() == 1) {
                    youngUserWithOneProducerList.add(user);
                }
            });
        });
        return youngUserWithOneProducerList;
    }
}
