package xb.com.refreshdemo.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

import xb.com.refreshdemo.Url;

/**
 * Created by KevenTao on 2017/4/14.
 */

public class User extends CheckEarnestInfo implements Serializable {


    private String age;
    private String birthday;
    private String avatar;
    private int gender; //性别,1 男, 2 女 ,
    private String nickName;
    private String phoneNumber;
    private int signUpSysterm;
    //个性签名
    private String signature;
    private int uId;
    private String password;
    //账户类型 普通账户:1(1<<0);超级管理员:2(1<<1);普通管理员:4(1<<2);可以组合,同时是多种身份:16(1<<4)掌律小秘书
    private int type;
    private String serviceType;//客服类型 8-4 售后  8-7 售前
    private boolean isAfterSale;//是否是售后客服
    private boolean isPreSale;//是否是售前客服
    //视听数
    private int avNum;
    //粉丝数
    private int fansNum;
    //关注数
    private int followingNum;
    //嘉宾数
    private int guestsNum;
    //用户标签id
    private String tagIds;
    private String tags;
    //0未认证,1审核中,2已认证,3未通过
    private int verifiedStatus;
    //0未关注，1已关注
    private int isFollowed;
    private String videoUId;
    // Auth
    private int thirdAuthType;
    private String thirdAuthToken;
    private String thirdAuthId;
    //微信第三方多加一个参数
    private String unionid;
    //第三个登录手机号码
    private String thirdBindPhoneNum;

    private int isFirstSignin = 0;

    private String nickNameModify;

    private String lawyerTags;
    private String lawyerTagsAll;//标签组合起来的字符串 用来给recyclerview 提高顺滑度
    private String lawyerIds;

    //咨询时长
    private int consultDuration;
    //咨询次数
    private int interactionNum;
    //视频直播次数
    private int liveNum;
    private int charge;//收费
    private String lawyerTagIds;
    private String location;
    private int onlineState;//在线状态0:离线,2:在线,1:咨询中 ,
    private int onlineSetState;//在线状态设置
    private double star;
    private String company; //所在学校
    private String tencentYunSig;
    private String sigName;
    //余额(当发起1v1咨询时,调用律师个人页接口，自己余额)
    private double balance;
    //用户可用时长分钟数
    private int minute;
    //0用户,1律师
    private int isLawyer;
    //免费时长
    private String freeConsult;
    //真实姓名
    private String realName;
    //收藏
    private int isFavorite;
    //执业年限
    private int seniority;
    //接通次数
    private int connectSuccessNum;
    //直联次数
    private int directConnectedNum;
    //抵用金
    private double frozen;
    //个人简介
    private String resume;
    //律师用户数
    private int lawyerUsers;
    //用户律师数
    private int userLawyers;
    private int myAnswerNum;//我的回答数
    private int myIssueNum;//用户-我的咨询
    private long billStartTime;
    private int isActive;//是否活跃  0为不活跃   1为活跃
    private int isHot;//是否火爆 0 不火爆 1 火爆
    private int isNew;//是否新秀 0 不是新秀 1 是新秀

    private String customTag;//自定义标签/我的标签

    private int billVideoNum;//直联次数
    private double coupon;//抵用券，单位分

    private int isRecommended;  //是否平台推荐   0 否  1 是

    private int interactiveLive;//是否是平台签约律师 用来判断是否可以开启互动直播 0 不能开 1 可以开
    //换字段 要求 如果小数点后面为零 去掉小数 如果小数点后第二位为0  只显示一位小数
    private int isInteractiveLive;//律师是否正在互动直播, 1是 0否
    private int liveId;//律师正在直播的liveid

    private int isBind;  //是否需要绑定手机号   0 否  1 是
    // 判断查询到的当前用户类型, 0 => 尚未注册的用户(*需要强制绑定手机号),
    // 1 => 以新流程方式注册的用户(可直接登录), 2 => 老用户已绑定第三方手机号的账户(可直接登录),
    // 3 => 老用户未绑定第三方手机号的账户(需要强制绑定手机号) 服务端使用
    private int userType;

    private int isOldUser; //1 是老用户 0 不是老用户
    private int isBindedRegistedPhone; //是否已绑定手机号已经注册过(0:没有注册,1:已注册)
    private int isOpenPay;//0 关闭 1 开启
    private int isOpenFree;//0 关闭 1 开启

    private int isBill;//是否赢得直联 0 否 1 是

    private int useImageType;//（0为使用头像，1为新上传图）
    private String thumbnail;//后台上传头像

    private String professionName;//所属专业
    private int professionId;//所属专业id

    private int userNum;//律师个人页 替换原来的粉丝
    private int verifiedType;//认证类型，1状元/状元家长，2学霸/学霸家长，3学咖/学咖家长
    private int totalVideoNum;//解答视频数

    private int maxBillPrice;//单价资费上限 ,
    private int minBillPrice;//单价资费下限 ,
    private int minLowBillPriceTime;//低价时长下限 ,
    private int maxLowBillPriceTime;//低价时长上限 ,
    private int drMaxBillPrice;//达人单价资费上限 ,
    private int drMinBillPrice;// 达人单价资费下限 ,
    private int drMaxLowBillPriceTime;//达人低价时长上限 ,
    private int drMinLowBillPriceTime;//达人低价时长下限 ,

    private String wechatUnionId;//用来接收掌律跳转过来时 微信登录使用的字段
    private int loginType;//用来接收掌律跳转过来时 掌律用户使用什么类型的账号登录的

    private int referralCode;//邀请码
    private int points;//积分
    private int directNextUserNum;//经销商直接下线人数
    private int friendNum;//普通用户好友数

    private int fxLevel;//经销商级别  0储备1初级2中级3高级
    private int isFxUser;//是否经销商，1是0否 -1支付礼包但未提交申请-2被取消资格的经销商-3被冻结的经销商

    private int lastFxUid;//上级经销商uid
    private int lastFriendUid;//上级好友uid

    private String fontColor;// 字体颜色
    private String backgroundColor;//背景颜色

    private List<String> callListText;//垂直跑马灯集合

    //预约
    private int startingPrice;//起步价 ,
    private int startingMinute;// 起步时间 ,单位是分
    private int appointment;//预约功能，0为未开启，1为开启 ,
    private int defaultStartingMinute;//专家默认起步时长
    private int defaultStartingPrice;//专家默认起步价
    private int drDefaultStartingMinute;//达人默认起步时长
    private int drDefaultStartingPrice;//达人默认起步价
    private int unpaidBillOrderNum;//待付款的预约订单数 ,
    private int unacceptedBillOrderNum;//待接收的预约订单数 ,
    private int inProgressBillOrderNum;//进行中的预约订单数 ,
    private int unsettledBillOrderNum;//待结算的预约订单数 ,
    private int uncommentBillOrderNum;//待评价的预约订单数 ,

    private String customerService;//法律小助手

    private long seniorityDate;//专家执业日期 状元高考时间用

    private String featuredLabel;//状元专用 特色标签


    private String relativeLabel;//家长已认证


    public int getUseImageType() {
        return useImageType;
    }

    public void setUseImageType(int useImageType) {
        this.useImageType = useImageType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getLawyerTags() {
        if (!TextUtils.isEmpty(lawyerTags)) {
            return lawyerTags;
        } else {
            return "未选取专业";
        }
    }

    public String getAvatarThubmnail() {
        if (!TextUtils.isEmpty(avatar)) {
            if (avatar.startsWith("http")) {
                return avatar;
            } else {
                return Url.FILE_UPLOAD_BASE_URL + avatar;
            }
        } else {
            return avatar;
        }
    }

    public int getIsBill() {
        return isBill;
    }

    public void setIsBill(int isBill) {
        this.isBill = isBill;
    }

    public int getMyIssueNum() {
        return myIssueNum;
    }

    public void setMyIssueNum(int myIssueNum) {
        this.myIssueNum = myIssueNum;
    }

    public double getCoupon() {
        return coupon / 100;
    }

    public void setCoupon(double coupon) {
        this.coupon = coupon;
    }

    public int getBillVideoNum() {
        return billVideoNum;
    }

    public void setBillVideoNum(int billVideoNum) {
        this.billVideoNum = billVideoNum;
    }

    public long getBillStartTime() {
        return billStartTime;
    }

    public void setBillStartTime(long billStartTime) {
        this.billStartTime = billStartTime;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getMyAnswerNum() {
        return myAnswerNum;
    }

    public void setMyAnswerNum(int myAnswerNum) {
        this.myAnswerNum = myAnswerNum;
    }

    public int getLawyerUsers() {
        return lawyerUsers;
    }

    public void setLawyerUsers(int lawyerUsers) {
        this.lawyerUsers = lawyerUsers;
    }

    public int getUserLawyers() {
        return userLawyers;
    }

    public void setUserLawyers(int userLawyers) {
        this.userLawyers = userLawyers;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public double getFrozen() {
        return frozen / 100;
    }

    public void setFrozen(double frozen) {
        this.frozen = frozen;
    }

    public int getConnectSuccessNum() {
        return connectSuccessNum;
    }

    public void setConnectSuccessNum(int connectSuccessNum) {
        this.connectSuccessNum = connectSuccessNum;
    }

    public int getDirectConnectedNum() {
        return directConnectedNum;
    }

    public void setDirectConnectedNum(int directConnectedNum) {
        this.directConnectedNum = directConnectedNum;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getOnlineSetState() {
        return onlineSetState;
    }

    public void setOnlineSetState(int onlineSetState) {
        this.onlineSetState = onlineSetState;
    }

    public String getRealName() {
        if (!TextUtils.isEmpty(realName)) {
//            if (AndroidUtil.getChampion() == getProfessionId()
//                    || AndroidUtil.getChampionParent() == getProfessionId()) {
//                if (AccountManager.getInstance().getCurrentUserId() > 0 &&
//                        AccountManager.getInstance().getCurrentUserId() == uId) {
//                    return realName;
//                } else {
//                    switch (realName.length() - 1) {
//                        case 0:
//                            return realName.substring(0, 1);
//                        case 1:
//                            return realName.substring(0, 1) + "*";
//                        case 2:
//                            return realName.substring(0, 1) + "**";
//                        default:
//                            return realName.substring(0, 1) + "***";
//                    }
//                }
//            } else {
            return realName;
//            }
        } else {
            if (!TextUtils.isEmpty(nickName)) {
                return nickName;
            } else {
                return "ID:" + uId;
            }
        }
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFreeConsult() {
        return freeConsult;
    }

    public void setFreeConsult(String freeConsult) {
        this.freeConsult = freeConsult;
    }

    public double getBalance() {
        return balance / 100;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setLawyerTags(String lawyerTags) {
        this.lawyerTags = lawyerTags;
    }

    public String getLawyerIds() {
        return lawyerIds;
    }

    public void setLawyerIds(String lawyerIds) {
        this.lawyerIds = lawyerIds;
    }

    public String getNickNameModify() {
        return nickName;
    }

    public void setNickNameModify(String nickNameModify) {
        this.nickNameModify = nickNameModify;
    }

    public int getIsFirstSignin() {
        return isFirstSignin;
    }

    public void setIsFirstSignin(int isFirstSignin) {
        this.isFirstSignin = isFirstSignin;
    }

    public String getAge() {
        return age;
    }


    public void setAge(String age) {
        this.age = age;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getAvatar() {
        return avatar;
    }

    //    public String getAvatarThubmnail() {
//        if (!StringUtils.isEmpty(avatar)) {
//            if (avatar.startsWith("http")) {
//                return avatar;
//            } else {
//                if (avatar.contains("width") || avatar.contains("height")) {
//                    return UrlConstant.FILE_UPLOAD_BASE_URL + avatar;
//                } else {
//                    return UrlConstant.FILE_UPLOAD_BASE_URL + avatar + "?width=150&height=150";
//                }
//            }
//        } else {
//            return avatar;
//        }
//    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean hasAvatar() {
        return !TextUtils.isEmpty(getAvatar());
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isReportorOrAdmin() {
        return getType() != 1;
    }


    public int getAvNum() {
        return avNum;
    }

    public void setAvNum(int avNum) {
        this.avNum = avNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }

    public int getGuestsNum() {
        return guestsNum;
    }

    public void setGuestsNum(int guestsNum) {
        this.guestsNum = guestsNum;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTags() {
        if (!TextUtils.isEmpty(tags)) {
            return tags;
        } else {
            return "未选取专业";
        }

    }


    public String getTagsInLive() {
        return tags;
    }


    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(int verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public boolean isFollowed() {
        return getIsFollowed() == 1;
    }

    public int getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(int isFollowed) {
        this.isFollowed = isFollowed;
    }

    public String getVideoUId() {
        return videoUId;
    }

    public void setVideoUId(String videoUId) {
        this.videoUId = videoUId;
    }

    public int getThirdAuthType() {
        return thirdAuthType;
    }

    public void setThirdAuthType(int thirdAuthType) {
        this.thirdAuthType = thirdAuthType;
    }

    public String getThirdAuthToken() {
        return thirdAuthToken;
    }

    public void setThirdAuthToken(String thirdAuthToken) {
        this.thirdAuthToken = thirdAuthToken;
    }

    public String getThirdAuthId() {
        return thirdAuthId;
    }

    public void setThirdAuthId(String thirdAuthId) {
        this.thirdAuthId = thirdAuthId;
    }

    public int getSignUpSysterm() {
        return signUpSysterm;
    }

    public void setSignUpSysterm(int signUpSysterm) {
        this.signUpSysterm = signUpSysterm;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getThirdBindPhoneNum() {
        return thirdBindPhoneNum;
    }

    public void setThirdBindPhoneNum(String thirdBindPhoneNum) {
        this.thirdBindPhoneNum = thirdBindPhoneNum;
    }

    public int getConsultDuration() {
        return consultDuration;
    }

    public void setConsultDuration(int consultDuration) {
        this.consultDuration = consultDuration;
    }

    public int getCharge() {
        return charge / 100;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getLawyerTagIds() {
        return lawyerTagIds;
    }

    public void setLawyerTagIds(String lawyerTagIds) {
        this.lawyerTagIds = lawyerTagIds;
    }

    public String getLocation() {
        if (!TextUtils.isEmpty(location)) {
            if (location.equals("请选择")) {
                return "";
            } else {
                return location;
            }
        } else {
            return "";
        }

    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(int onlineState) {
        this.onlineState = onlineState;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getIsLawyer() {
        return isLawyer;
    }

    public void setIsLawyer(int isLawyer) {
        this.isLawyer = isLawyer;
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

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum) {
        this.liveNum = liveNum;
    }

    public int getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(int isRecommended) {
        this.isRecommended = isRecommended;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            return ((User) o).getuId() == this.getuId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) this.getuId();
    }

    public int getInteractiveLive() {
        return interactiveLive;
    }

    public void setInteractiveLive(int interactiveLive) {
        this.interactiveLive = interactiveLive;
    }

    public int getIsInteractiveLive() {
        return isInteractiveLive;
    }

    public void setIsInteractiveLive(int isInteractiveLive) {
        this.isInteractiveLive = isInteractiveLive;
    }

    public int getLiveId() {
        return liveId;
    }

    public void setLiveId(int liveId) {
        this.liveId = liveId;
    }

    public String getLawyerTagsAll() {
        return lawyerTagsAll;
    }

    public void setLawyerTagsAll(String lawyerTagsAll) {
        this.lawyerTagsAll = lawyerTagsAll;
    }

    public int getIsBind() {
        return isBind;
    }

    public void setIsBind(int isBind) {
        this.isBind = isBind;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIsOldUser() {
        return isOldUser;
    }

    public void setIsOldUser(int isOldUser) {
        this.isOldUser = isOldUser;
    }


    public int getIsBindedRegistedPhone() {
        return isBindedRegistedPhone;
    }

    public void setIsBindedRegistedPhone(int isBindedRegistedPhone) {
        this.isBindedRegistedPhone = isBindedRegistedPhone;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public int getIsOpenPay() {
        return isOpenPay;
    }

    public void setIsOpenPay(int isOpenPay) {
        this.isOpenPay = isOpenPay;
    }

    public int getIsOpenFree() {
        return isOpenFree;
    }

    public void setIsOpenFree(int isOpenFree) {
        this.isOpenFree = isOpenFree;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getVerifiedType() {
        return verifiedType;
    }

    public void setVerifiedType(int verifiedType) {
        this.verifiedType = verifiedType;
    }

    public int getTotalVideoNum() {
        return totalVideoNum;
    }

    public void setTotalVideoNum(int totalVideoNum) {
        this.totalVideoNum = totalVideoNum;
    }

    public int getMaxBillPrice() {
        return maxBillPrice;
    }

    public void setMaxBillPrice(int maxBillPrice) {
        this.maxBillPrice = maxBillPrice;
    }

    public int getMinBillPrice() {
        return minBillPrice;
    }

    public void setMinBillPrice(int minBillPrice) {
        this.minBillPrice = minBillPrice;
    }

    public int getMinLowBillPriceTime() {
        return minLowBillPriceTime;
    }

    public void setMinLowBillPriceTime(int minLowBillPriceTime) {
        this.minLowBillPriceTime = minLowBillPriceTime;
    }

    public int getMaxLowBillPriceTime() {
        return maxLowBillPriceTime;
    }

    public void setMaxLowBillPriceTime(int maxLowBillPriceTime) {
        this.maxLowBillPriceTime = maxLowBillPriceTime;
    }

    public int getDrMaxBillPrice() {
        return drMaxBillPrice;
    }

    public void setDrMaxBillPrice(int drMaxBillPrice) {
        this.drMaxBillPrice = drMaxBillPrice;
    }

    public int getDrMinBillPrice() {
        return drMinBillPrice;
    }

    public void setDrMinBillPrice(int drMinBillPrice) {
        this.drMinBillPrice = drMinBillPrice;
    }

    public int getDrMaxLowBillPriceTime() {
        return drMaxLowBillPriceTime;
    }

    public void setDrMaxLowBillPriceTime(int drMaxLowBillPriceTime) {
        this.drMaxLowBillPriceTime = drMaxLowBillPriceTime;
    }

    public int getDrMinLowBillPriceTime() {
        return drMinLowBillPriceTime;
    }

    public void setDrMinLowBillPriceTime(int drMinLowBillPriceTime) {
        this.drMinLowBillPriceTime = drMinLowBillPriceTime;
    }

    public String getWechatUnionId() {
        return wechatUnionId;
    }

    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(int referralCode) {
        this.referralCode = referralCode;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDirectNextUserNum() {
        return directNextUserNum;
    }

    public void setDirectNextUserNum(int directNextUserNum) {
        this.directNextUserNum = directNextUserNum;
    }

    public int getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(int friendNum) {
        this.friendNum = friendNum;
    }

    public int getFxLevel() {
        return fxLevel;
    }

    public String getFxLevelMsg() {
        if (fxLevel == 0) {
            return "储备";
        } else if (fxLevel == 1) {
            return "初级";
        } else if (fxLevel == 2) {
            return "中级";
        } else if (fxLevel == 3) {
            return "高级";
        }
        return "储备";
    }

    public void setFxLevel(int fxLevel) {
        this.fxLevel = fxLevel;
    }

    public int getIsFxUser() {
        return isFxUser;
    }

    public void setIsFxUser(int isFxUser) {
        this.isFxUser = isFxUser;
    }

    public int getLastFxUid() {
        return lastFxUid;
    }

    public void setLastFxUid(int lastFxUid) {
        this.lastFxUid = lastFxUid;
    }

    public int getLastFriendUid() {
        return lastFriendUid;
    }

    public void setLastFriendUid(int lastFriendUid) {
        this.lastFriendUid = lastFriendUid;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<String> getCallListText() {
        return callListText;
    }

    public void setCallListText(List<String> callListText) {
        this.callListText = callListText;
    }

    public int getStartingPrice() {
        return startingPrice / 100;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getStartingMinute() {
        return startingMinute;
    }

    public void setStartingMinute(int startingMinute) {
        this.startingMinute = startingMinute;
    }

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public int getDefaultStartingMinute() {
        return defaultStartingMinute;
    }

    public void setDefaultStartingMinute(int defaultStartingMinute) {
        this.defaultStartingMinute = defaultStartingMinute;
    }

    public int getDefaultStartingPrice() {
        return defaultStartingPrice;
    }

    public void setDefaultStartingPrice(int defaultStartingPrice) {
        this.defaultStartingPrice = defaultStartingPrice;
    }

    public int getDrDefaultStartingMinute() {
        return drDefaultStartingMinute;
    }

    public void setDrDefaultStartingMinute(int drDefaultStartingMinute) {
        this.drDefaultStartingMinute = drDefaultStartingMinute;
    }

    public int getDrDefaultStartingPrice() {
        return drDefaultStartingPrice / 100;
    }

    public void setDrDefaultStartingPrice(int drDefaultStartingPrice) {
        this.drDefaultStartingPrice = drDefaultStartingPrice;
    }

    public int getUnacceptedBillOrderNum() {
        return unacceptedBillOrderNum;
    }

    public void setUnacceptedBillOrderNum(int unacceptedBillOrderNum) {
        this.unacceptedBillOrderNum = unacceptedBillOrderNum;
    }

    public int getInProgressBillOrderNum() {
        return inProgressBillOrderNum;
    }

    public void setInProgressBillOrderNum(int inProgressBillOrderNum) {
        this.inProgressBillOrderNum = inProgressBillOrderNum;
    }

    public int getUnsettledBillOrderNum() {
        return unsettledBillOrderNum;
    }

    public void setUnsettledBillOrderNum(int unsettledBillOrderNum) {
        this.unsettledBillOrderNum = unsettledBillOrderNum;
    }

    public int getUncommentBillOrderNum() {
        return uncommentBillOrderNum;
    }

    public void setUncommentBillOrderNum(int uncommentBillOrderNum) {
        this.uncommentBillOrderNum = uncommentBillOrderNum;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


    public boolean isAfterSale() {//是否是售后
        if (!TextUtils.isEmpty(serviceType) &&
                (serviceType.contains("8-4") || serviceType.contains("8-7"))) {
            isAfterSale = true;
            return isAfterSale;
        }
        isAfterSale = false;
        return isAfterSale;
    }

    public void setAfterSale(boolean afterSale) {
        isAfterSale = afterSale;
    }

    public void setPreSale(boolean preSale) {
        isPreSale = preSale;
    }


    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    public long getSeniorityDate() {
        return seniorityDate;
    }

    public void setSeniorityDate(long seniorityDate) {
        this.seniorityDate = seniorityDate;
    }

    public String getFeaturedLabel() {
        return featuredLabel;
    }

    public void setFeaturedLabel(String featuredLabel) {
        this.featuredLabel = featuredLabel;
    }

    public int getUnpaidBillOrderNum() {
        return unpaidBillOrderNum;
    }

    public void setUnpaidBillOrderNum(int unpaidBillOrderNum) {
        this.unpaidBillOrderNum = unpaidBillOrderNum;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag;
    }

    public String getRelativeLabel() {
        return relativeLabel;
    }

    public void setRelativeLabel(String relativeLabel) {
        this.relativeLabel = relativeLabel;
    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "User{" +
                "age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", signUpSysterm=" + signUpSysterm +
                ", signature='" + signature + '\'' +
                ", uId=" + uId +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", serviceType='" + serviceType + '\'' +
                ", isAfterSale=" + isAfterSale +
                ", isPreSale=" + isPreSale +
                ", avNum=" + avNum +
                ", fansNum=" + fansNum +
                ", followingNum=" + followingNum +
                ", guestsNum=" + guestsNum +
                ", tagIds='" + tagIds + '\'' +
                ", tags='" + tags + '\'' +
                ", verifiedStatus=" + verifiedStatus +
                ", isFollowed=" + isFollowed +
                ", videoUId='" + videoUId + '\'' +
                ", thirdAuthType=" + thirdAuthType +
                ", thirdAuthToken='" + thirdAuthToken + '\'' +
                ", thirdAuthId='" + thirdAuthId + '\'' +
                ", unionid='" + unionid + '\'' +
                ", thirdBindPhoneNum='" + thirdBindPhoneNum + '\'' +
                ", isFirstSignin=" + isFirstSignin +
                ", nickNameModify='" + nickNameModify + '\'' +
                ", lawyerTags='" + lawyerTags + '\'' +
                ", lawyerTagsAll='" + lawyerTagsAll + '\'' +
                ", lawyerIds='" + lawyerIds + '\'' +
                ", consultDuration=" + consultDuration +
                ", interactionNum=" + interactionNum +
                ", liveNum=" + liveNum +
                ", charge=" + charge +
                ", lawyerTagIds='" + lawyerTagIds + '\'' +
                ", location='" + location + '\'' +
                ", onlineState=" + onlineState +
                ", onlineSetState=" + onlineSetState +
                ", star=" + star +
                ", company='" + company + '\'' +
                ", tencentYunSig='" + tencentYunSig + '\'' +
                ", sigName='" + sigName + '\'' +
                ", balance=" + balance +
                ", minute=" + minute +
                ", isLawyer=" + isLawyer +
                ", freeConsult='" + freeConsult + '\'' +
                ", realName='" + realName + '\'' +
                ", isFavorite=" + isFavorite +
                ", seniority=" + seniority +
                ", connectSuccessNum=" + connectSuccessNum +
                ", directConnectedNum=" + directConnectedNum +
                ", frozen=" + frozen +
                ", resume='" + resume + '\'' +
                ", lawyerUsers=" + lawyerUsers +
                ", userLawyers=" + userLawyers +
                ", myAnswerNum=" + myAnswerNum +
                ", myIssueNum=" + myIssueNum +
                ", billStartTime=" + billStartTime +
                ", isActive=" + isActive +
                ", isHot=" + isHot +
                ", isNew=" + isNew +
                ", customTag='" + customTag + '\'' +
                ", billVideoNum=" + billVideoNum +
                ", coupon=" + coupon +
                ", isRecommended=" + isRecommended +
                ", interactiveLive=" + interactiveLive +
                ", isInteractiveLive=" + isInteractiveLive +
                ", liveId=" + liveId +
                ", isBind=" + isBind +
                ", userType=" + userType +
                ", isOldUser=" + isOldUser +
                ", isBindedRegistedPhone=" + isBindedRegistedPhone +
                ", isOpenPay=" + isOpenPay +
                ", isOpenFree=" + isOpenFree +
                ", isBill=" + isBill +
                ", useImageType=" + useImageType +
                ", thumbnail='" + thumbnail + '\'' +
                ", professionName='" + professionName + '\'' +
                ", professionId=" + professionId +
                ", userNum=" + userNum +
                ", verifiedType=" + verifiedType +
                ", totalVideoNum=" + totalVideoNum +
                ", maxBillPrice=" + maxBillPrice +
                ", minBillPrice=" + minBillPrice +
                ", minLowBillPriceTime=" + minLowBillPriceTime +
                ", maxLowBillPriceTime=" + maxLowBillPriceTime +
                ", drMaxBillPrice=" + drMaxBillPrice +
                ", drMinBillPrice=" + drMinBillPrice +
                ", drMaxLowBillPriceTime=" + drMaxLowBillPriceTime +
                ", drMinLowBillPriceTime=" + drMinLowBillPriceTime +
                ", wechatUnionId='" + wechatUnionId + '\'' +
                ", loginType=" + loginType +
                ", referralCode=" + referralCode +
                ", points=" + points +
                ", directNextUserNum=" + directNextUserNum +
                ", friendNum=" + friendNum +
                ", fxLevel=" + fxLevel +
                ", isFxUser=" + isFxUser +
                ", lastFxUid=" + lastFxUid +
                ", lastFriendUid=" + lastFriendUid +
                ", fontColor='" + fontColor + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", callListText=" + callListText +
                ", startingPrice=" + startingPrice +
                ", startingMinute=" + startingMinute +
                ", appointment=" + appointment +
                ", defaultStartingMinute=" + defaultStartingMinute +
                ", defaultStartingPrice=" + defaultStartingPrice +
                ", drDefaultStartingMinute=" + drDefaultStartingMinute +
                ", drDefaultStartingPrice=" + drDefaultStartingPrice +
                ", unpaidBillOrderNum=" + unpaidBillOrderNum +
                ", unacceptedBillOrderNum=" + unacceptedBillOrderNum +
                ", inProgressBillOrderNum=" + inProgressBillOrderNum +
                ", unsettledBillOrderNum=" + unsettledBillOrderNum +
                ", uncommentBillOrderNum=" + uncommentBillOrderNum +
                ", customerService='" + customerService + '\'' +
                ", seniorityDate=" + seniorityDate +
                ", featuredLabel='" + featuredLabel + '\'' +
                ", relativeLabel='" + relativeLabel + '\'' +
                '}';
    }
}
