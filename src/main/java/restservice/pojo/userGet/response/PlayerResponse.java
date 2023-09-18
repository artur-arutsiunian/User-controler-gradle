package restservice.pojo.userGet.response;

import java.util.Objects;

public class PlayerResponse {
    private Integer id;
    private Integer age;
    private String gender;
    private String role;
    private String screenName;

    public PlayerResponse() {
    }

    public PlayerResponse(Integer id, Integer age, String gender, String role, String screenName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.screenName = screenName;
    }

    public PlayerResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public PlayerResponse setAge(Integer age) {
        this.age = age;
        return this;
    }

    public PlayerResponse setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PlayerResponse setRole(String role) {
        this.role = role;
        return this;
    }

    public PlayerResponse setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResponse that = (PlayerResponse) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(id, that.id) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, role, screenName);
    }
}
