package xb.com.refreshdemo.bean.message;


import java.io.Serializable;
import java.util.List;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [zhanglv-android, 2018/03/18 10:57]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class MessageModel implements Serializable {
    public static final int TYPE_MY_MSG = 0;//用户看到的自己消息
    public static final int TYPE_OTHER_MSG = 1;//对方消息
    public static final int TYPE_lAWYER_MSG = 2;//律师界面看到的自己消息
    public static final int TYPE_WINDOWS_MSG = 3;//系统消息
    public static final int TYPE_GUIDE_MSG = 4;//导学团
    private int msgType;//自己用户适配器区分的type 0 自己 1 其他用户


    private int uId;
    private String phoneNumber;//
    private String nickName;//昵称
    private String realName;//真实姓名
    private String avatar;//头像
    private int gender;//性别
    private int age;//年龄
    private String signature;//个性签名 一句话介绍自己 ,
    private String tags;//用户标签
    private String tagIds;//用户标签id
    private int signUpSysterm;//
    private int followingNum;//
    private int fansNum;//粉丝数量
    private int guestsNum;//
    private int verifiedStatus;//
    private String videoUId;//
    private String thirdBindPhoneNum;//
    private String lawyerTags;//
    private String lawyerTagIds;//
    private double star;//
    private int onlineState;//
    private int charge;//
    private int consultDuration;//
    private String company;//
    private String tencentYunSig;//
    private String sigName;//
    private int interactionNum;//
    private int seniority;//
    private int onlineSetState;//
    private int interactiveLive;//
    private int groupId;//
    private int senderUid;//
    private String name;//
    private int memberId;//
    private int unReadNum;//
    private int userType;//
    private int issueId;//
    private int totalNum;//
    private String lawTag;//
    private int lawTagId;//
    private int senderId;//
    private int liveCount;//
    private long updated;//
    private int sameQuestion;//
    private int isSameQuestion;//
    private String source;//
    private int isShow;//
    private int earnestMoneyAmount;//
    private int consultingStates;////免费咨询和付费咨询状态，1是答复期，2是已还款，3是未到期，4是已过期，5是赢得直联"
    private int liveNum;//
    private int commonMessageId;//
    private int commonMessageGroupId;//
    private int commonMessageSenderUid;//
    private String actionUrl;//
    private String content;//
    private int type;//消息内容类型 0:纯文本 1:纯图片 2:未接听类型 3:直联完成 4:直播回答中（直播中）
    // 5:直播回答生成中（直播完成，视频还未生成） 6:直播回答完成（直播完成，视频生成成功） 7,10:掌律小秘 8:预约消息 9:音频 11:客服小秘书
    private int groupType;//0 1对1消息列表  1 一对多消息列表 2系统消息 3 客服消息
    private String latestMessage;//
    private int latestMessageUserType;//
    private int latestMessageType;//消息内容类型 0:纯文本 1:纯图片 2:未接听类型 3:直联完成 4:直播回答中（直播中）
    // 5:直播回答生成中（直播完成，视频还未生成） 6:直播回答完成（直播完成，视频生成成功） 7:掌律小秘 8:预约消息
    private long created;//

    //专属律师
    private int favoriteLawyerId;//专属律师id
    private int favoriteUserUid;
    private int favoriteCreated;


    //这2个id 只在1对1时使用
    private int userUid;//用户id
    private int lawyerUid;//律师id
    private String imageWidth;//图片宽度
    private String imageHeight;//图片高度
    private int otherId;//对方id 一对一情况下使用 永远是当前用户点进去时对方的id

    private int professionId;
    private String professionName;//领域标签
    private int verifiedType;//认证类型，1状元/状元家长，2学霸/学霸家长，3学咖/学咖家长

    private int billId;
    private int billOrderId;

    private int style;//1，此消息为免费转付费标记；0，无意义

    private List<String> guideavatar;//导学团群组头像

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public int getSignUpSysterm() {
        return signUpSysterm;
    }

    public void setSignUpSysterm(int signUpSysterm) {
        this.signUpSysterm = signUpSysterm;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getGuestsNum() {
        return guestsNum;
    }

    public void setGuestsNum(int guestsNum) {
        this.guestsNum = guestsNum;
    }

    public int getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(int verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getVideoUId() {
        return videoUId;
    }

    public void setVideoUId(String videoUId) {
        this.videoUId = videoUId;
    }

    public String getThirdBindPhoneNum() {
        return thirdBindPhoneNum;
    }

    public void setThirdBindPhoneNum(String thirdBindPhoneNum) {
        this.thirdBindPhoneNum = thirdBindPhoneNum;
    }

    public String getLawyerTags() {
        return lawyerTags;
    }

    public void setLawyerTags(String lawyerTags) {
        this.lawyerTags = lawyerTags;
    }

    public String getLawyerTagIds() {
        return lawyerTagIds;
    }

    public void setLawyerTagIds(String lawyerTagIds) {
        this.lawyerTagIds = lawyerTagIds;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(int onlineState) {
        this.onlineState = onlineState;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getConsultDuration() {
        return consultDuration;
    }

    public void setConsultDuration(int consultDuration) {
        this.consultDuration = consultDuration;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTencentYunSig() {
        return tencentYunSig;
    }

    public void setTencentYunSig(String tencentYunSig) {
        this.tencentYunSig = tencentYunSig;
    }

    public String getSigName() {
        return sigName;
    }

    public void setSigName(String sigName) {
        this.sigName = sigName;
    }

    public int getInteractionNum() {
        return interactionNum;
    }

    public void setInteractionNum(int interactionNum) {
        this.interactionNum = interactionNum;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getOnlineSetState() {
        return onlineSetState;
    }

    public void setOnlineSetState(int onlineSetState) {
        this.onlineSetState = onlineSetState;
    }

    public int getInteractiveLive() {
        return interactiveLive;
    }

    public void setInteractiveLive(int interactiveLive) {
        this.interactiveLive = interactiveLive;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(int senderUid) {
        this.senderUid = senderUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getLawTag() {
        return lawTag;
    }

    public void setLawTag(String lawTag) {
        this.lawTag = lawTag;
    }

    public int getLawTagId() {
        return lawTagId;
    }

    public void setLawTagId(int lawTagId) {
        this.lawTagId = lawTagId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(int liveCount) {
        this.liveCount = liveCount;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getSameQuestion() {
        return sameQuestion;
    }

    public void setSameQuestion(int sameQuestion) {
        this.sameQuestion = sameQuestion;
    }

    public int getIsSameQuestion() {
        return isSameQuestion;
    }

    public void setIsSameQuestion(int isSameQuestion) {
        this.isSameQuestion = isSameQuestion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getEarnestMoneyAmount() {
        return earnestMoneyAmount;
    }

    public void setEarnestMoneyAmount(int earnestMoneyAmount) {
        this.earnestMoneyAmount = earnestMoneyAmount;
    }

    public int getConsultingStates() {
        return consultingStates;
    }

    public void setConsultingStates(int consultingStates) {
        this.consultingStates = consultingStates;
    }

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public int getCommonMessageId() {
        return commonMessageId;
    }

    public void setCommonMessageId(int commonMessageId) {
        this.commonMessageId = commonMessageId;
    }

    public int getCommonMessageGroupId() {
        return commonMessageGroupId;
    }

    public void setCommonMessageGroupId(int commonMessageGroupId) {
        this.commonMessageGroupId = commonMessageGroupId;
    }

    public int getCommonMessageSenderUid() {
        return commonMessageSenderUid;
    }

    public void setCommonMessageSenderUid(int commonMessageSenderUid) {
        this.commonMessageSenderUid = commonMessageSenderUid;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public int getLatestMessageUserType() {
        return latestMessageUserType;
    }

    public void setLatestMessageUserType(int latestMessageUserType) {
        this.latestMessageUserType = latestMessageUserType;
    }

    public int getLatestMessageType() {
        return latestMessageType;
    }

    public void setLatestMessageType(int latestMessageType) {
        this.latestMessageType = latestMessageType;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getFavoriteLawyerId() {
        return favoriteLawyerId;
    }

    public void setFavoriteLawyerId(int favoriteLawyerId) {
        this.favoriteLawyerId = favoriteLawyerId;
    }

    public int getFavoriteUserUid() {
        return favoriteUserUid;
    }

    public void setFavoriteUserUid(int favoriteUserUid) {
        this.favoriteUserUid = favoriteUserUid;
    }

    public int getFavoriteCreated() {
        return favoriteCreated;
    }

    public void setFavoriteCreated(int favoriteCreated) {
        this.favoriteCreated = favoriteCreated;
    }

    public int getUserUid() {
        return userUid;
    }

    public void setUserUid(int userUid) {
        this.userUid = userUid;
    }

    public int getLawyerUid() {
        return lawyerUid;
    }

    public void setLawyerUid(int lawyerUid) {
        this.lawyerUid = lawyerUid;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public int getVerifiedType() {
        return verifiedType;
    }

    public void setVerifiedType(int verifiedType) {
        this.verifiedType = verifiedType;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getBillOrderId() {
        return billOrderId;
    }

    public void setBillOrderId(int billOrderId) {
        this.billOrderId = billOrderId;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public List<String> getGuideavatar() {
        return guideavatar;
    }

    public void setGuideavatar(List<String> guideavatar) {
        this.guideavatar = guideavatar;
    }
}
