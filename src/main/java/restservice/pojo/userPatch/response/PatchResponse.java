package restservice.pojo.userPatch.response;

import java.util.Objects;

public class PatchResponse {

    private Integer age;
    private String gender;
    private Integer id;
    private String login;
    private String role;
    private String screenName;

    public PatchResponse(Integer age, String gender, Integer id, String login, String role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.login = login;
        this.role = role;
        this.screenName = screenName;
    }

    public PatchResponse() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatchResponse that = (PatchResponse) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, role, screenName);
    }

    @Override
    public String toString() {
        return "PatchRes{" +
                "age=" + age +
                ", gender=" + gender +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
