package com.sunhao.entity;

import com.sunhao.common.ConstantClass;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 项目名称：sunhaocms
 * 类 名 称：User
 * 类 描 述：TODO
 * 创建时间：2019/11/13 1:02 下午
 * 创 建 人：sunhao
 */
public class User implements Serializable {


    private static final long serialVersionUID = 4866301898722593351L;
    //
    private Integer id;
    //
    private String username;
    private String password;
    //昵称没有用上
    private String nickname;
    //生日
    private Date birthday;
    // 性别
    private int gender;
    //是否被解禁
    private int locked;
    //注册时间
    private Date createTime;
    private Date updateTime;
    //没有用上
    private String url;
    // 没有用上
    private int score;
    //权限设置
    private int role = ConstantClass.USER_ROLE_GENERAL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return gender == user.gender &&
                locked == user.locked &&
                score == user.score &&
                role == user.role &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(updateTime, user.updateTime) &&
                Objects.equals(url, user.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, nickname, birthday, gender, locked, createTime, updateTime, url, score, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", locked=" + locked +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", url='" + url + '\'' +
                ", score=" + score +
                ", role=" + role +
                '}';
    }
}
