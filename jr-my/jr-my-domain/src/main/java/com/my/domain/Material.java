package com.my.domain;

public class Material {
    private Long materialId;

    private String materialUrl;

    private String materialPassword;

    private String uploader;

    private String materialName;

    private String materialSummary;

    private Long cid;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public String getMaterialPassword() {
        return materialPassword;
    }

    public void setMaterialPassword(String materialPassword) {
        this.materialPassword = materialPassword;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSummary() {
        return materialSummary;
    }

    public void setMaterialSummary(String materialSummary) {
        this.materialSummary = materialSummary;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
}