package com.smartbus.heze.checkup.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/4/26.
 */

public class RCJCNewList implements Serializable {

    /**
     * success : true
     * totalCounts : 2
     * result : [{"version":null,"orgId":null,"orgPath":null,"id":1973,"driverId":"103953","driverName":"张亚林","carNo":"鲁R08678D","busCode":"18678","depId":"497","depName":"城乡公交一车队","lineCode":"609","kaoheDate":"2020-04-26","examiner":"刘钦邦","note":"测试","shry":"","shsj":"","shzt":"","question":"票款、安全设备","contentzt":"","way":"跟车","result":"我觉得可以","checkzt":"1","photo":null,"kaoheTime":"09:30:56","rectification":"1"},{"version":null,"orgId":null,"orgPath":null,"id":1974,"driverId":"103917","driverName":"黄玉合","carNo":"鲁RK9910","busCode":"59910","depId":"497","depName":"城乡公交一车队","lineCode":"611","kaoheDate":"2020-04-26","examiner":"刘钦邦","note":"测试","shry":"","shsj":"","shzt":"","question":"q","contentzt":"1","way":"站点","result":"q","checkzt":"1","photo":null,"kaoheTime":"09:40:37","rectification":"1"}]
     */

    private boolean success;
    private int totalCounts;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * version : null
         * orgId : null
         * orgPath : null
         * id : 1973
         * driverId : 103953
         * driverName : 张亚林
         * carNo : 鲁R08678D
         * busCode : 18678
         * depId : 497
         * depName : 城乡公交一车队
         * lineCode : 609
         * kaoheDate : 2020-04-26
         * examiner : 刘钦邦
         * note : 测试
         * shry :
         * shsj :
         * shzt :
         * question : 票款、安全设备
         * contentzt :
         * way : 跟车
         * result : 我觉得可以
         * checkzt : 1
         * photo : null
         * kaoheTime : 09:30:56
         * rectification : 1
         */

        private Object version;
        private Object orgId;
        private Object orgPath;
        private int id;
        private String driverId;
        private String driverName;
        private String carNo;
        private String busCode;
        private String depId;
        private String depName;
        private String lineCode;
        private String kaoheDate;
        private String examiner;
        private String note;
        private String shry;
        private String shsj;
        private String shzt;
        private String question;
        private String contentzt;
        private String way;
        private String result;
        private String checkzt;
        private Object photo;
        private String kaoheTime;
        private String rectification;

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public Object getOrgPath() {
            return orgPath;
        }

        public void setOrgPath(Object orgPath) {
            this.orgPath = orgPath;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDriverId() {
            return driverId;
        }

        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getBusCode() {
            return busCode;
        }

        public void setBusCode(String busCode) {
            this.busCode = busCode;
        }

        public String getDepId() {
            return depId;
        }

        public void setDepId(String depId) {
            this.depId = depId;
        }

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getLineCode() {
            return lineCode;
        }

        public void setLineCode(String lineCode) {
            this.lineCode = lineCode;
        }

        public String getKaoheDate() {
            return kaoheDate;
        }

        public void setKaoheDate(String kaoheDate) {
            this.kaoheDate = kaoheDate;
        }

        public String getExaminer() {
            return examiner;
        }

        public void setExaminer(String examiner) {
            this.examiner = examiner;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getShry() {
            return shry;
        }

        public void setShry(String shry) {
            this.shry = shry;
        }

        public String getShsj() {
            return shsj;
        }

        public void setShsj(String shsj) {
            this.shsj = shsj;
        }

        public String getShzt() {
            return shzt;
        }

        public void setShzt(String shzt) {
            this.shzt = shzt;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getContentzt() {
            return contentzt;
        }

        public void setContentzt(String contentzt) {
            this.contentzt = contentzt;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getCheckzt() {
            return checkzt;
        }

        public void setCheckzt(String checkzt) {
            this.checkzt = checkzt;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public String getKaoheTime() {
            return kaoheTime;
        }

        public void setKaoheTime(String kaoheTime) {
            this.kaoheTime = kaoheTime;
        }

        public String getRectification() {
            return rectification;
        }

        public void setRectification(String rectification) {
            this.rectification = rectification;
        }
    }
}
