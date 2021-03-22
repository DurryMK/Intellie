package com.intellie.data.entity.question;


import com.intellie.common.entity.system.BaseEntity;
/**
 * @author durry
 * @version 1.0
 * @date 2021/3/14 11:35
 * @describe:题目的
 */
public class Question extends BaseEntity {

    private static final long serialVersionUID = 8676303584279244634L;

    public static final String NO_DEL = "0";

    public static final String DEL = "1";

    /**
     * 题目信息实体类
     */
    private String id;
    private String title;//题目标题
    private String origin;//原题链接
    private String type;//题目类型
    private String level;//难度
    private String site;//来源网站
    private String from;//题目出处
    private String time;//创建时间
    private String content;//内容
    private String owner;//创建者
    private String del;//
    private String createType;//创建方式
    private String correct;//正确答案
    private String options;//可选项
    /**
     * 只读属性
     * */
    private String isAdd;//是否已经收藏
    private double score;//分值
    private String levelStr;//难度
    private int sort;//排序
    private String paperId;//所属试卷
    private String preView;//预览内容

    public String getLevelStr() {
        return levelStr;
    }

    public void setLevelStr(String levelStr) {
        this.levelStr = levelStr;
    }

    public String getPreView() {
        return preView;
    }

    public void setPreView(String preView) {
        this.preView = preView;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", site='" + site + '\'' +
                ", from='" + from + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", owner='" + owner + '\'' +
                ", del='" + del + '\'' +
                ", createType='" + createType + '\'' +
                ", correct='" + correct + '\'' +
                ", options='" + options + '\'' +
                '}';
    }

    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
