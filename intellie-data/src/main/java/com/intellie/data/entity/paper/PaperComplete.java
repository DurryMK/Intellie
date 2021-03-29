package com.intellie.data.entity.paper;

/**
 * @author durry
 * @version 1.0
 * @date 2021/3/14 11:35
 * @describe:试卷的完整属性
 */
public class PaperComplete {
    private String id;//试卷序号
    private String title;//标题
    private String remark;//描述
    private String typeId;//类型id
    private String type;//类型
    private String code;//唯一编号
    private String owner;//创建者
    private String imgUrl;//封面图片路径
    private String level;//难度
    private String time;//创建时间
    private String del;//是否被删除
    private String personal;//私密或者公开
    private String status;//发布或未发布
    private String join;//允许参加考试的次数
    private String isOpenForever;
    private String page;//允许切换页面的次数
    private String isCopy;//是否允许复制
    private String isPaste;//是否允许粘贴
    private String isRname;//是否仅允许实名考生参加
    private String leave;//允许离开摄像头界面的次数
    private String isCamera;//是否开启摄像头监考
    private String isDoRname;//是否考前进行人脸识别
    private String duration;//单次考试时长
    private String delivery;//是否限时开放
    private String start;//开始时间
    private String end;//结束时间
    private String passMark;//及格的评语
    private String noMark;//不及格的评语
    private String isShowResult;//是否在考试结束后立即显示成绩
    private String submit;//是否允许随时提交试卷
    private String runningStatus;//是否正在进行中
    private String runningStatusStr;//
    private int questionNum;//题数
    private long totalScore;//总分
    private String examLink;//考试链接

    public String getExamLink() {
        return examLink;
    }

    public void setExamLink(String examLink) {
        this.examLink = examLink;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getIsOpenForever() {
        return isOpenForever;
    }

    public void setIsOpenForever(String isOpenForever) {
        this.isOpenForever = isOpenForever;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(String isCopy) {
        this.isCopy = isCopy;
    }

    public String getIsPaste() {
        return isPaste;
    }

    public void setIsPaste(String isPaste) {
        this.isPaste = isPaste;
    }

    public String getIsRname() {
        return isRname;
    }

    public void setIsRname(String isRname) {
        this.isRname = isRname;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getIsCamera() {
        return isCamera;
    }

    public void setIsCamera(String isCamera) {
        this.isCamera = isCamera;
    }

    public String getIsDoRname() {
        return isDoRname;
    }

    public void setIsDoRname(String isDoRname) {
        this.isDoRname = isDoRname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPassMark() {
        return passMark;
    }

    public void setPassMark(String passMark) {
        this.passMark = passMark;
    }

    public String getNoMark() {
        return noMark;
    }

    public void setNoMark(String noMark) {
        this.noMark = noMark;
    }

    public String getIsShowResult() {
        return isShowResult;
    }

    public void setIsShowResult(String isShowResult) {
        this.isShowResult = isShowResult;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus;
    }

    public String getRunningStatusStr() {
        return runningStatusStr;
    }

    public void setRunningStatusStr(String runningStatusStr) {
        this.runningStatusStr = runningStatusStr;
    }

    @Override
    public String toString() {
        return "PaperComplete{" +
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
                ", personal='" + personal + '\'' +
                ", status='" + status + '\'' +
                ", join='" + join + '\'' +
                ", isOpenForever='" + isOpenForever + '\'' +
                ", page='" + page + '\'' +
                ", isCopy='" + isCopy + '\'' +
                ", isPaste='" + isPaste + '\'' +
                ", isRname='" + isRname + '\'' +
                ", leave='" + leave + '\'' +
                ", isCamera='" + isCamera + '\'' +
                ", isDoRname='" + isDoRname + '\'' +
                ", duration='" + duration + '\'' +
                ", delivery='" + delivery + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", passMark='" + passMark + '\'' +
                ", noMark='" + noMark + '\'' +
                ", isShowResult='" + isShowResult + '\'' +
                ", submit='" + submit + '\'' +
                '}';
    }
}
