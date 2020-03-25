package com.smartbus.heze.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/7/20.
 */

public class WillDoListType implements Serializable {

    /**
     * success : true
     * data : [{"version":null,"orgId":null,"orgPath":null,"proTypeId":2101,"typeName":"公共流程","path":"0.2101.","depth":1,"parentId":0,"nodeKey":"gonggongliucheng","catKey":"FLOW","sn":1,"userId":1,"code":null,"useTime":null,"usePrice":null,"wxfl":1}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * version : null
         * orgId : null
         * orgPath : null
         * proTypeId : 2101
         * typeName : 公共流程
         * path : 0.2101.
         * depth : 1
         * parentId : 0
         * nodeKey : gonggongliucheng
         * catKey : FLOW
         * sn : 1
         * userId : 1
         * code : null
         * useTime : null
         * usePrice : null
         * wxfl : 1
         */

        private Object version;
        private Object orgId;
        private Object orgPath;
        private String proTypeId;
        private String typeName;
        private String path;
        private String depth;
        private String parentId;
        private String nodeKey;
        private String catKey;
        private String sn;
        private String userId;
        private Object code;
        private Object useTime;
        private String usePrice;
        private String wxfl;

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

        public String getProTypeId() {
            return proTypeId;
        }

        public void setProTypeId(String proTypeId) {
            this.proTypeId = proTypeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getDepth() {
            return depth;
        }

        public void setDepth(String depth) {
            this.depth = depth;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getNodeKey() {
            return nodeKey;
        }

        public void setNodeKey(String nodeKey) {
            this.nodeKey = nodeKey;
        }

        public String getCatKey() {
            return catKey;
        }

        public void setCatKey(String catKey) {
            this.catKey = catKey;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getUseTime() {
            return useTime;
        }

        public void setUseTime(Object useTime) {
            this.useTime = useTime;
        }

        public String getUsePrice() {
            return usePrice;
        }

        public void setUsePrice(String usePrice) {
            this.usePrice = usePrice;
        }

        public String getWxfl() {
            return wxfl;
        }

        public void setWxfl(String wxfl) {
            this.wxfl = wxfl;
        }
    }
}
