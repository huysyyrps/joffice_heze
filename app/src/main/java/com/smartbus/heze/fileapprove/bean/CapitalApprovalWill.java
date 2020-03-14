package com.smartbus.heze.fileapprove.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/3/10.
 */

public class CapitalApprovalWill implements Serializable {


    /**
     * success : true
     * taskId : 11770014
     * pkValue : 40
     * formRights : {'sbdate':'1','caiwujingli':'1','sbmemo':'1','zongjingliyijian':'1','keshiyijian':'2','caiwuke':'1','caiwuyijian':'1','sbrname':'1','zongjingli':'1','sbdepName':'1','caiwjingli':'1','fujian':'1','sbtype':'1'}
     * mainform : [{"caiwjingli":"","keshiyijian":"","bumenyijian":"","caiwujingli":"","runId":55004,"$type$":"WF_caiwujieKuanzhiChu","zongjingli":"","fujian":"","sbdate":"2020-03-11","caiwuyijian":"","sbmemo":"逆行","sbtype":"测试","sbdepName":"安全科","mainId":40,"caiwuke":"","sbrname":"安守亮","zongjingliyijian":"","dataUrl_save":"/joffice/hrm/updateFinanceAmount.do?id=91"}]
     * formDefId : 54
     * preTaskName :
     * isSignTask : false
     * trans : [{"destType":"task","destination":"财务科长","name":"to 用户任务","source":"科室领导"}]
     * runId : 55004
     */

    private boolean success;
    private String taskId;
    private String pkValue;
    private String formRights;
    private String formDefId;
    private String preTaskName;
    private boolean isSignTask;
    private int runId;
    private List<MainformBean> mainform;
    private List<TransBean> trans;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPkValue() {
        return pkValue;
    }

    public void setPkValue(String pkValue) {
        this.pkValue = pkValue;
    }

    public String getFormRights() {
        return formRights;
    }

    public void setFormRights(String formRights) {
        this.formRights = formRights;
    }

    public String getFormDefId() {
        return formDefId;
    }

    public void setFormDefId(String formDefId) {
        this.formDefId = formDefId;
    }

    public String getPreTaskName() {
        return preTaskName;
    }

    public void setPreTaskName(String preTaskName) {
        this.preTaskName = preTaskName;
    }

    public boolean isIsSignTask() {
        return isSignTask;
    }

    public void setIsSignTask(boolean isSignTask) {
        this.isSignTask = isSignTask;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public List<MainformBean> getMainform() {
        return mainform;
    }

    public void setMainform(List<MainformBean> mainform) {
        this.mainform = mainform;
    }

    public List<TransBean> getTrans() {
        return trans;
    }

    public void setTrans(List<TransBean> trans) {
        this.trans = trans;
    }

    public static class MainformBean implements Serializable {
        /**
         * caiwjingli :
         * keshiyijian :
         * bumenyijian :
         * caiwujingli :
         * runId : 55004
         * $type$ : WF_caiwujieKuanzhiChu
         * zongjingli :
         * fujian :
         * sbdate : 2020-03-11
         * caiwuyijian :
         * sbmemo : 逆行
         * sbtype : 测试
         * sbdepName : 安全科
         * mainId : 40
         * caiwuke :
         * sbrname : 安守亮
         * zongjingliyijian :
         * dataUrl_save : /joffice/hrm/updateFinanceAmount.do?id=91
         */

        private String caiwjingli;
        private String keshiyijian;
        private String bumenyijian;
        private String caiwujingli;
        private String runId;
        private String $type$;
        private String zongjingli;
        private String fujian;
        private String sbdate;
        private String caiwuyijian;
        private String sbmemo;
        private String sbtype;
        private String sbdepName;
        private int mainId;
        private String caiwuke;
        private String sbrname;
        private String zongjingliyijian;
        private String dataUrl_save;

        public String getCaiwjingli() {
            return caiwjingli;
        }

        public void setCaiwjingli(String caiwjingli) {
            this.caiwjingli = caiwjingli;
        }

        public String getKeshiyijian() {
            return keshiyijian;
        }

        public void setKeshiyijian(String keshiyijian) {
            this.keshiyijian = keshiyijian;
        }

        public String getBumenyijian() {
            return bumenyijian;
        }

        public void setBumenyijian(String bumenyijian) {
            this.bumenyijian = bumenyijian;
        }

        public String getCaiwujingli() {
            return caiwujingli;
        }

        public void setCaiwujingli(String caiwujingli) {
            this.caiwujingli = caiwujingli;
        }

        public String getRunId() {
            return runId;
        }

        public void setRunId(String runId) {
            this.runId = runId;
        }

        public String get$type$() {
            return $type$;
        }

        public void set$type$(String $type$) {
            this.$type$ = $type$;
        }

        public String getZongjingli() {
            return zongjingli;
        }

        public void setZongjingli(String zongjingli) {
            this.zongjingli = zongjingli;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }

        public String getSbdate() {
            return sbdate;
        }

        public void setSbdate(String sbdate) {
            this.sbdate = sbdate;
        }

        public String getCaiwuyijian() {
            return caiwuyijian;
        }

        public void setCaiwuyijian(String caiwuyijian) {
            this.caiwuyijian = caiwuyijian;
        }

        public String getSbmemo() {
            return sbmemo;
        }

        public void setSbmemo(String sbmemo) {
            this.sbmemo = sbmemo;
        }

        public String getSbtype() {
            return sbtype;
        }

        public void setSbtype(String sbtype) {
            this.sbtype = sbtype;
        }

        public String getSbdepName() {
            return sbdepName;
        }

        public void setSbdepName(String sbdepName) {
            this.sbdepName = sbdepName;
        }

        public int getMainId() {
            return mainId;
        }

        public void setMainId(int mainId) {
            this.mainId = mainId;
        }

        public String getCaiwuke() {
            return caiwuke;
        }

        public void setCaiwuke(String caiwuke) {
            this.caiwuke = caiwuke;
        }

        public String getSbrname() {
            return sbrname;
        }

        public void setSbrname(String sbrname) {
            this.sbrname = sbrname;
        }

        public String getZongjingliyijian() {
            return zongjingliyijian;
        }

        public void setZongjingliyijian(String zongjingliyijian) {
            this.zongjingliyijian = zongjingliyijian;
        }

        public String getDataUrl_save() {
            return dataUrl_save;
        }

        public void setDataUrl_save(String dataUrl_save) {
            this.dataUrl_save = dataUrl_save;
        }
    }

    public static class TransBean implements Serializable {
        /**
         * destType : task
         * destination : 财务科长
         * name : to 用户任务
         * source : 科室领导
         */

        private String destType;
        private String destination;
        private String name;
        private String source;

        public String getDestType() {
            return destType;
        }

        public void setDestType(String destType) {
            this.destType = destType;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }
}
