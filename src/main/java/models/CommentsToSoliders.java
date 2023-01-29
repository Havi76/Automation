package models;

import framwork.annotations.database.Column;
import framwork.annotations.database.Database;
import framwork.annotations.database.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Database(property = "updatable-mefakdim-db")
@Table(name = "Comments_To_Soldiers", isIdGenerated = true)
@Data
@Accessors(fluent = true)
public class CommentsToSoliders {
    @Column("Comment_Id")
    private Long commentId;

    @Column("Mispar_Ishi")
    private String misparIshi;

    @Column("Comment_Text")
    private String commentText;

    @Column("Writer_Id")
    private String writerId;

    @Column("Writer_First_Name")
    private String writerFirstName;

    @Column("Writer_Last_Name")
    private String writerLastName;

    @Column("Writer_Position")
    private String writerPosition;

    @Column("Comment_Time")
    private LocalDateTime commentTime;

    @Column("Is_Old")
    private Boolean isOld;

    @Column("Reminder_Time")
    private LocalDateTime reminderTime;
}
