package net.springcome.winlotto.entity;

/**
 * 사용자 정보
 */
public class User {
    private String _id;             //
    private String userId;          // 사용자 아이디 (자동생성)
    private String userNm;          // 사용자 이름
    private String userEmail;       // 사용자 이메일
    private String userPwd;         // 사용자 비밀번호
    private String userGrad;        // 사용자 등급
    private String useUseDate;      // 사용자 사용시작일
    private String userJoinDate;    // 사용자 회원가입 일자

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserGrad() {
        return userGrad;
    }

    public void setUserGrad(String userGrad) {
        this.userGrad = userGrad;
    }

    public String getUseUseDate() {
        return useUseDate;
    }

    public void setUseUseDate(String useUseDate) {
        this.useUseDate = useUseDate;
    }

    public String getUserJoinDate() {
        return userJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.userJoinDate = userJoinDate;
    }
}
