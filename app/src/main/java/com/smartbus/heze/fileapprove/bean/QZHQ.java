package com.smartbus.heze.fileapprove.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/5/5.
 */

public class QZHQ implements Serializable {

    /**
     * success : true
     * data : [{"version":null,"orgId":null,"orgPath":null,"picId":1,"userId":"1","fullname":"超级管理员","runId":"86936","picString":"/9j/4AAQSkZJRgABAQAAAQABAAD/4gIoSUNDX1BST0ZJTEUAAQEAAAIYAAAAAAIQAABtbnRyUkdCIFhZWiAAAAAAAAAAAAAAAABhY3NwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAA9tYAAQAAAADTLQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlkZXNjAAAA8AAAAHRyWFlaAAABZAAAABRnWFlaAAABeAAAABRiWFlaAAABjAAAABRyVFJDAAABoAAAAChnVFJDAAABoAAAAChiVFJDAAABoAAAACh3dHB0AAAByAAAABRjcHJ0AAAB3AAAADxtbHVjAAAAAAAAAAEAAAAMZW5VUwAAAFgAAAAcAHMAUgBHAEIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFhZWiAAAAAAAABvogAAOPUAAAOQWFlaIAAAAAAAAGKZAAC3hQAAGNpYWVogAAAAAAAAJKAAAA+EAAC2z3BhcmEAAAAAAAQAAAACZmYAAPKnAAANWQAAE9AAAApbAAAAAAAAAABYWVogAAAAAAAA9tYAAQAAAADTLW1sdWMAAAAAAAAAAQAAAAxlblVTAAAAIAAAABwARwBvAG8AZwBsAGUAIABJAG4AYwAuACAAMgAwADEANv/bAEMABgQFBgUEBgYFBgcHBggKEAoKCQkKFA4PDBAXFBgYFxQWFhodJR8aGyMcFhYgLCAjJicpKikZHy0wLSgwJSgpKP/bAEMBBwcHCggKEwoKEygaFhooKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKP/AABEIACEAHQMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAAAgMHCAEEBgX/xAAmEAACAQQBAQkBAAAAAAAAAAABAgMABAURBiEHCBITIzFBUWGh/8QAGQEAAgMBAAAAAAAAAAAAAAAAAAMCBAUB/8QAIBEAAQQCAgMBAAAAAAAAAAAAAQACAxEEBRMxEjJBYf/aAAwDAQACEQMRAD8AtPv2pOtHegKS8ipEXchVUbJPxUbwds3FHyM9q81ykUe9XTxenJr611/lRLwz2KsQYc+TfCwurulJu/qiuC4Z2m4PlmSksLITw3IBZUnUDzAPcromu9Fda4PFhQnx5cZ3HM2itTI2q3lhc2z7CTRmM6+iNVTDlXHr3jOZmx2RiKMjenJrpInw4/Ku18V5ObwWLzkCQ5azgu40bxKJUDaP5SMiDmFLY0O7dqZCSLae1APd94neXfJIuQzRvFYWisImI15rkFOn4ATVkgNgdaZt7eO3iWKBVjiQeFUUaAFOqPCNe9MhiETPEKpttm/ZZByHCvgH4nKxRRTAs34iiiihC//Z","activityName":"总经理审批","fieldName":"zjlqz"}]
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
         * picId : 1
         * userId : 1
         * fullname : 超级管理员
         * runId : 86936
         * picString : /9j/4AAQSkZJRgABAQAAAQABAAD/4gIoSUNDX1BST0ZJTEUAAQEAAAIYAAAAAAIQAABtbnRyUkdCIFhZWiAAAAAAAAAAAAAAAABhY3NwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAA9tYAAQAAAADTLQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlkZXNjAAAA8AAAAHRyWFlaAAABZAAAABRnWFlaAAABeAAAABRiWFlaAAABjAAAABRyVFJDAAABoAAAAChnVFJDAAABoAAAAChiVFJDAAABoAAAACh3dHB0AAAByAAAABRjcHJ0AAAB3AAAADxtbHVjAAAAAAAAAAEAAAAMZW5VUwAAAFgAAAAcAHMAUgBHAEIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFhZWiAAAAAAAABvogAAOPUAAAOQWFlaIAAAAAAAAGKZAAC3hQAAGNpYWVogAAAAAAAAJKAAAA+EAAC2z3BhcmEAAAAAAAQAAAACZmYAAPKnAAANWQAAE9AAAApbAAAAAAAAAABYWVogAAAAAAAA9tYAAQAAAADTLW1sdWMAAAAAAAAAAQAAAAxlblVTAAAAIAAAABwARwBvAG8AZwBsAGUAIABJAG4AYwAuACAAMgAwADEANv/bAEMABgQFBgUEBgYFBgcHBggKEAoKCQkKFA4PDBAXFBgYFxQWFhodJR8aGyMcFhYgLCAjJicpKikZHy0wLSgwJSgpKP/bAEMBBwcHCggKEwoKEygaFhooKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKP/AABEIACEAHQMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAAAgMHCAEEBgX/xAAmEAACAQQBAQkBAAAAAAAAAAABAgMABAURBiEHCBITIzFBUWGh/8QAGQEAAgMBAAAAAAAAAAAAAAAAAAMCBAUB/8QAIBEAAQQCAgMBAAAAAAAAAAAAAQACAxEEBRMxEjJBYf/aAAwDAQACEQMRAD8AtPv2pOtHegKS8ipEXchVUbJPxUbwds3FHyM9q81ykUe9XTxenJr611/lRLwz2KsQYc+TfCwurulJu/qiuC4Z2m4PlmSksLITw3IBZUnUDzAPcromu9Fda4PFhQnx5cZ3HM2itTI2q3lhc2z7CTRmM6+iNVTDlXHr3jOZmx2RiKMjenJrpInw4/Ku18V5ObwWLzkCQ5azgu40bxKJUDaP5SMiDmFLY0O7dqZCSLae1APd94neXfJIuQzRvFYWisImI15rkFOn4ATVkgNgdaZt7eO3iWKBVjiQeFUUaAFOqPCNe9MhiETPEKpttm/ZZByHCvgH4nKxRRTAs34iiiihC//Z
         * activityName : 总经理审批
         * fieldName : zjlqz
         */

        private Object version;
        private Object orgId;
        private Object orgPath;
        private int picId;
        private String userId;
        private String fullname;
        private String runId;
        private String picString;
        private String activityName;
        private String fieldName;

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

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getRunId() {
            return runId;
        }

        public void setRunId(String runId) {
            this.runId = runId;
        }

        public String getPicString() {
            return picString;
        }

        public void setPicString(String picString) {
            this.picString = picString;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }
}
