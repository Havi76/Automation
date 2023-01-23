package models;

import framwork.annotations.database.Column;
import framwork.annotations.database.Database;
import framwork.annotations.database.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Database(property = "updatable-mefakdim-db")
@Table(name = "Notifications", isIdGenerated = true)
@Data
@Accessors(fluent = true)
public class Notifications {
    @Column("Id")
    private Long id;

    @Column("Action_Type")
    private String actionType;

    @Column("CreateAt")
    private LocalDate createAt;

    @Column("Seen_Ind")
    private Boolean seenIdn;
}
