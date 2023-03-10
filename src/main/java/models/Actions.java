package models;

import framework.annotations.database.Column;
import framework.annotations.database.Database;
import framework.annotations.database.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Database(property = "updatable-mefakdim-db")
@Table(name = "Actions", isIdGenerated = true)
@Data
@Accessors(fluent = true)
public class Actions {
    @Column("Created_By")
    private String createdBy;

    @Column("Created_At")
    private LocalDate createAt;

    @Column("Action_ID")
    private Long actionId;
}
