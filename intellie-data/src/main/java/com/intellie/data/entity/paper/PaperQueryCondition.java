package com.intellie.data.entity.paper;

import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/15 10:11
 * @describe:查询试卷的条件
 */
public class PaperQueryCondition extends BaseEntity {
    private static final long serialVersionUID = 1906423039792419127L;

      private int currentPage;

      private int pageSize;

      private int startIndex;

      private int total;

      private String key;

      private String owner;

      private String personal;

      private String del;

      private String isPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getIsPage() {
        return isPage;
    }

    public void setIsPage(String isPage) {
        this.isPage = isPage;
    }

    @Override
    public String toString() {
        return "PaperQueryCondition{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", startIndex=" + startIndex +
                ", total=" + total +
                ", key='" + key + '\'' +
                ", owner='" + owner + '\'' +
                ", personal='" + personal + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
