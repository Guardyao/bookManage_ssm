package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class borrowDetails {
    int id;
    int sid;
    String user_name;
    int bid;
    String book_title;
    Date time;
}
