package models;

import framework.annotations.database.Column;
import framework.annotations.database.Database;
import framework.annotations.database.Table;
import lombok.Data;
import lombok.experimental.Accessors;

@Database(property = "updatable-mefakdim-db")
@Table(name = "Interviews", isIdGenerated = true)
@Data
@Accessors(fluent = true)
public class Interviews {
    @Column("Interview_Id")
    private Long interviewId;

    @Column(("Interviewer_Id"))
    private String interviewerId;
}
