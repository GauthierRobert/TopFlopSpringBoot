package com.lhc.datamodel.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Data
public class SystemData {

    @Column(unique=true, name = "REFERENCE")
    private String reference;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @Column(name = "CREATION_TIME")
    private LocalTime creationTime;

    @Column(name = "LAST_MODIFICATION_DATE")
    private LocalDate modificationDate;

    @Column(name = "LAST_MODIFICATION_TIME")
    private LocalTime modificationTime;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "COUNT_MODIFIED")
    private int countModified;

    public SystemData(String reference, String createdBy, LocalDate creationDate, LocalTime creationTime, LocalDate modificationDate, LocalTime modificationTime, String modifiedBy, int countModified) {
        this.reference = reference;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.modificationDate = modificationDate;
        this.modificationTime = modificationTime;
        this.modifiedBy = modifiedBy;
        this.countModified = countModified;
    }

    public SystemData() {
    }

    public static SystemData systemData(String reference, String createdBy){
        return new SystemData(reference, createdBy, LocalDate.now(), LocalTime.now(), null, null, null, 0);
    }

    public static SystemData updated(SystemData systemData, String modifiedBy){
        return new SystemData(systemData.getReference(),
                systemData.getCreatedBy(),
                systemData.getCreationDate(),
                systemData.getCreationTime(),
                LocalDate.now(),
                LocalTime.now(),
                modifiedBy,
                systemData.getCountModified() + 1);

    }
}
