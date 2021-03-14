package com.intellie.data.entity.paper;


import com.intellie.common.entity.system.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 19:43
 * 试卷基本信息实体类
 */
public class Paper extends BaseEntity {

    private static final long serialVersionUID = -102700627461469183L;
    public static final String DEL = "1";
    public static final String NO_DEL = "0";

    private String id;//试卷序号
    private String title;//标题
    private String remark;//描述
    private String type;//类型
    private String code;//唯一编号
    private String owner;//创建者
    private String imgUrl;//封面图片路径
    private String level;//难度
    private String time;//创建时间
    private String del;//是否被删除
    private String modifyTime;//修改时间

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", owner='" + owner + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", level='" + level + '\'' +
                ", time='" + time + '\'' +
                ", del='" + del + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}
