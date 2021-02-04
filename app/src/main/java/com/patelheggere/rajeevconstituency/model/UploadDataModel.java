package com.patelheggere.rajeevconstituency.model;

public class UploadDataModel {
    public String name;
    public String mobile;
    public String purpose, remarks="", entered_by="", updated_by="", booth_no="", visit_count="";
    public String village, epic="", religion="", caste="", indication="", status="", department="";

    public UploadDataModel() {
    }

    public UploadDataModel(String name, String mobile, String purpose, String remarks, String entered_by, String updated_by, String booth_no, String visit_count, String village, String epic, String religion, String caste, String indication, String status, String department) {
        this.name = name;
        this.mobile = mobile;
        this.purpose = purpose;
        this.remarks = remarks;
        this.entered_by = entered_by;
        this.updated_by = updated_by;
        this.booth_no = booth_no;
        this.visit_count = visit_count;
        this.village = village;
        this.epic = epic;
        this.religion = religion;
        this.caste = caste;
        this.indication = indication;
        this.status = status;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEntered_by() {
        return entered_by;
    }

    public void setEntered_by(String entered_by) {
        this.entered_by = entered_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getBooth_no() {
        return booth_no;
    }

    public void setBooth_no(String booth_no) {
        this.booth_no = booth_no;
    }

    public String getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(String visit_count) {
        this.visit_count = visit_count;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
