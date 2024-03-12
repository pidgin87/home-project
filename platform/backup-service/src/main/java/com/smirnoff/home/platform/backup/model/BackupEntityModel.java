package com.smirnoff.home.platform.backup.model;

import lombok.Data;

import java.util.Map;

@Data
public class BackupEntityModel {
    private Map<String, String> fields;
    private Boolean deleted = Boolean.FALSE;
    private BackupEntityMetaModel meta;
}
