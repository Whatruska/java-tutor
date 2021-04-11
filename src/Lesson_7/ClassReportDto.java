package Lesson_7;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClassReportDto {
    private long peopleCount;
    private String preferedFormat;
}
