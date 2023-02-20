package models;

import framework.annotations.database.Column;
import framework.annotations.database.Database;
import framework.annotations.database.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Database(property = "updatable-mefakdim-db")
@Table(name = "Notifications", isIdGenerated = true)
@Data
@Accessors(fluent = true)
public class Notifications {
    @Column("Id")
    private Integer id;

    @Column("Action_Type")
    private String actionType;

    @Column("CreateAt")
    private LocalDate createAt;

    @Column("Seen_Ind")
    private Boolean seenIdn;

    @Column("Action_Id")
    private Integer actionId;
}
