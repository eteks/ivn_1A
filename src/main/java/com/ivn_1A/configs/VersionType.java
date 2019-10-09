/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.configs;

/**
 *
 * @author ETS-05
 */
public enum VersionType {
    Pdbversion(1),
    Legislationversion(2),
    Safetyversion(3),
    Featureversion(4),
    IVN_Version(5),
    ACBversion(6);

    private Integer versionCode;

    VersionType(int versionCode) {
        this.versionCode = versionCode;
    }

    public static VersionType fromId(Integer versionCode) {
        for (VersionType at : VersionType.values()) {
            if (at.getVersionCode().equals(versionCode)) {
                return at;
            }
        }
        return null;
    }

    public Integer getVersionCode() {
        return this.versionCode;
    }
}
