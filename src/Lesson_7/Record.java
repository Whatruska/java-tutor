package Lesson_7;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class Record {
    private String date;
    private String fio;
    private String group;
    private Set<String> classes;
    private String format;
}
