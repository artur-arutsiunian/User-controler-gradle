package restservice.pojo.userCreate.response;

import java.util.Objects;

public class CreateResponse {
    private Integer age;
    private String gender;
    private Integer id;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public CreateResponse() {
    }

    private CreateResponse(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public CreateResponse setAge(Integer age) {
        this.age = age;
        return this;
    }

    public CreateResponse setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public CreateResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public CreateResponse setLogin(String login) {
        this.login = login;
        return this;
    }

    public CreateResponse setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateResponse setRole(String role) {
        this.role = role;
        return this;
    }

    public CreateResponse setScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateResponse that = (CreateResponse) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "CreateResponse{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    public static class Builder {

        private Integer age;
        private String gender;
        private Integer id;
        private String login;
        private String password;
        private String role;
        private String screenName;

        public Builder buildAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder buildGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder buildId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder buildLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder buildRole(String role) {
            this.role = role;
            return this;
        }

        public Builder buildScreenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public CreateResponse build() {
            return new CreateResponse(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(age, builder.age) && Objects.equals(gender, builder.gender) && Objects.equals(id, builder.id) && Objects.equals(login, builder.login) && Objects.equals(password, builder.password) && Objects.equals(role, builder.role) && Objects.equals(screenName, builder.screenName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, gender, id, login, password, role, screenName);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "age='" + age + '\'' +
                    ", gender='" + gender + '\'' +
                    ", id='" + id + '\'' +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    ", screenName='" + screenName + '\'' +
                    '}';
        }
    }
}
